import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.*;
import java.text.SimpleDateFormat;
import java.util.concurrent.ConcurrentHashMap;


public class AMEDYSystem
{
    static Logger systemLogger = createLogger("AMEDY", Level.WARNING, true, true);

    private SystemManager systemManeger;
    private ConcurrentHashMap<String ,User> allUsers;
    private Authentication authentication;
    private DataBase database;

    /***
     * Constructor.
     *
     * @param authentication authentication system to will use to verify users.
     */
    public AMEDYSystem(Authentication authentication)
    {
        this.authentication = authentication;
        this.database = authentication.getDB();
    }

    /***
     * First system initialization.
     * In this process the syste will ask for System Manager Details.
     * 
     * @UC 1.1
     */
    public void boot()
    {
        AMEDYSystem.systemLogger.info("trying to connect to External Systems.");

        //Union Accounting System
        AMEDYSystem.systemLogger.info("trying to connect to Union Accounting System.");
        while(!connectToUnionAccountingSystem()) {
            AMEDYSystem.systemLogger.info("failed to connect to Union Accounting System.");
        }

        SendAlert("connect to Union Accounting System successfully");

        //Country Law System
        AMEDYSystem.systemLogger.info("trying to connect to Country Law System.");
        while(!connectToCountryLawSystem()) {
            AMEDYSystem.systemLogger.info("failed to connect to Country Law System.");
        }

        SendAlert("connect to Country Law System successfully");

        SendAlert("connect to External Systems successfully");

//        buildDBTables();

        //SystemManager details
        AMEDYSystem.systemLogger.info("starting get system manager from user");
        String[] systemManagerFillDetails = {"username", "password", "name"};
        List userAnswer = GetDetailsFromUser(systemManagerFillDetails);

        boolean userRegister = false;
        while(!userRegister) {

            if (userRegister = RegisterUserToDB(userAnswer)) {

                AMEDYSystem.systemLogger.info("register system manager successfully");

            } else {
                AMEDYSystem.systemLogger.warning("failed to register system manager");

                AMEDYSystem.systemLogger.info("trying ti get system manager from user again");
                userAnswer = GetDetailsFromUser(systemManagerFillDetails);
            }
        }

        //Assign authorization
        AMEDYSystem.systemLogger.info("set system manager authorization");

        boolean userAuthorization = false;
        while(!userAuthorization) {

            String username = ((Pair)userAnswer.get(0)).getValue();

            if (userAuthorization = SetAuthorization(username, "System Manager")) {
                AMEDYSystem.systemLogger.info("set system manager authorization successfully");

            } else {
                AMEDYSystem.systemLogger.warning("failed to set system manager authorization");
            }
        }

        SendAlert("System Manager set successfully");
    }

    private void buildDBTables() {
        List<String> createStmt = new ArrayList<>();
        String createUsers = "CREATE TABLE \"Users\" (\n" +
                "\t\"userType\"\tTEXT,\n" +
                "\t\"username\"\tTEXT,\n" +
                "\t\"password\"\tTEXT,\n" +
                "\t\"name\"\tTEXT,\n" +
                "\t\"qualification\"\tTEXT,\n" +
                "\t\"birthday\"\tTEXT,\n" +
                "\t\"role\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"username\")\n" +
                ")";
        String createAuthorization = "CREATE TABLE \"Authorization\" (\n" +
                "\t\"username\"\tTEXT,\n" +
                "\t\"permissionLevel\"\tINTEGER\n" +
                ")";

        createStmt.add(createUsers);
        createStmt.add(createAuthorization);

        CRUD.createTable("System", createStmt);
    }


    public void logoutUser(User user)
    {

    }

    /**
     * Use-Case 2.2
     * registering a new user to the system. the new user will be a fan user.
     */
    public void signup()
    {
        systemLogger.info("[AMEDYsystem][signup] entered signup method - taking username argument from user...");
        Scanner sc = new Scanner(System.in);
        System.out.println("enter username");
        String username = sc.nextLine();
        while(!verifyUsername(username))
        {
            systemLogger.info("[AMEDYsystem][signup] - user entered invalid username - trying again to take username.");
            System.out.println("please enter a new username:");
            username = sc.nextLine();
        }
        systemLogger.info("[AMEDYsystem][signup] - succeeded taking username from user - taking password argument from user.");
        System.out.println("please enter password:");
        String password = sc.nextLine();
        System.out.println("please re-enter your password:");
        String reEnterPassword = sc.next();
        while(!verifyPassword(password, reEnterPassword))
        {
            systemLogger.info("[AMEDYsystem][signup] -user inserted invalid password - trying to take password argument from user.");
            System.out.println("please enter password:");
            password = sc.nextLine();
            System.out.println("please re-enter your password:");
            reEnterPassword = sc.next();
        }
        systemLogger.info("[AMEDYsystem][signup] -succeeded taking password from user - taking name argument from user.");
        System.out.println("please enter your name:");
        String name = sc.nextLine();

        while(!verifyName(name))
        {
            systemLogger.info("[AMEDYsystem][signup] -user inserted invalid name - trying again to take name argument from user.");
            System.out.println("please enter your name:");
            name = sc.nextLine();
        }
        String finalUsername = username;
        String finalPassword = password;
        String finalName = name;
        List<Pair> data = new ArrayList<Pair>(){{
            add(new Pair("userType","Fan"));
           add(new Pair("username", finalUsername));
           add(new Pair("password", finalPassword));
           add(new Pair("name", finalName));
        }};

        systemLogger.info("[AMEDYsystem][signup] - signing the new user to the DataBase.");
        CRUD.createUser("System", data);
        systemLogger.info("[AMEDYsystem][signup] - assigning authorizations to the new user.");
        CRUD.createAuthorization("System", username, "Fan");
    }


    /**
     * the method checks if the name inserted bvy the user is at list 2 characters and conatins only a-zA-Z characters.
     * @param name - String. the name inserted by the user.
     * @return false - if the name is invalid, else - return true.
     */
    private boolean verifyName(String name)
    {
        if(name.length() <= 1)
        {
            systemLogger.info("[AMEDYsystem][verifyName] - name inserted by the users is too short.");
            System.out.println("name have to be at list 2 characters long.");
            return false;
        }
        if(!(name.matches("[a-zA-Z]+")))
        {
            systemLogger.info("[AMEDYsystem][verifyName] - name inserted by the users contains other characters than a-zA-Z.");
            System.out.println("name have to be only characters a-zA-Z.");
            return false;
        }
        return true;
    }

    /**
     *the method checks if the inserted password by the user is at list 6 characters long and he entered the password twice correctly.
     * @param password - String. the first time the user entered his password.
     * @param reEnterPassword - String. the second time the user entered his password.
     * @return false - if the the password is invalid, else - return true.
     */
    private boolean verifyPassword(String password, String reEnterPassword)
    {
        if(password.length() < 6)
        {
            systemLogger.info("[AMEDYsystem][verifyPassword] - passwords inserted by the users is too short.");
            System.out.println("password have to be at list 6 characters long");
        }
        if(!password.equals(reEnterPassword))
        {
            systemLogger.info("[AMEDYsystem][verifyPassword] - passwords inserted by the users doesnt match.");
            System.out.println("passwords doesnt match.");
            return false;
        }

        return true;
    }

    /**
     *the method checks if the username inserted by the user is at list 6 characters long and doesnt contains spaces.
     * @param username - String. the username inserted by the user.
     * @return false - if the username is invalid. else - return true.
     */
    private boolean verifyUsername(String username)
    {
        if(username.length() < 6 )
        {
            systemLogger.info("[AMEDYsystem][verifyUsername] - username inserted by user was too short.");
            System.out.println("username is too short.");
            return false;
        }

        for(char ch: username.toCharArray())
        {
            if (ch == ' ')
            {
                systemLogger.info("[AMEDYsystem][verifyUsername] - username inserted by user included spaces.");
                System.out.println("spaces is not allowed.");
                return false;
            }
        }
        List<Pair> params = new ArrayList<Pair>(){{
           add(new Pair("username", username));
        }};

        ResultSet results = CRUD.select("System","Users",params);

        boolean emptyResultSet;

        try {
            emptyResultSet = results.next();

        } catch (SQLException e) {
            emptyResultSet = true;
        }

        if (emptyResultSet)
        {
            systemLogger.info("[AMEDYsystem][verifyUsername] - username inserted by user is already exist in the system.");
            System.out.println("this username is already exist in the system.");

            return false;
        }

        return true;
    }

    /***
     * Connecting to Law System.
     * @return
     */
    private boolean connectToCountryLawSystem() {
        return true;  //TODO: implement this method
    }

    /***
     * Connecting to Union Accounting System.
     * @return
     */
    private boolean connectToUnionAccountingSystem() {
        return true;  //TODO: implement this method
    }

    /**
     * Raise the given message to the User.
     * @param message
     */
    private void SendAlert(String message) {
        AMEDYSystem.systemLogger.info(message);
        System.out.println(message);
        //TODO: raise a alert to user(WEB\GUI)
    }

    /**
     * Get details from user. get String Array with all the relevant fields
     * and ask the  user field by field what his answer.
     * return String array with all user answer.
     *
     * @param systemManagerDetails String array containing all details required.
     *
     * @return string array with user answers.
     */
    private List GetDetailsFromUser(String[] systemManagerDetails) {

        Scanner sc = new Scanner(System.in);
        List<Pair> userAnswer = new ArrayList<>();

        Pair entry = null;
        for(int i = 0 ; i < systemManagerDetails.length; i++) {
            System.out.println(String.format("Please enter %s:", systemManagerDetails[i]));

            entry = new Pair(systemManagerDetails[i], sc.nextLine());
            userAnswer.add(entry);
        }

        return userAnswer;
    }

    private boolean RegisterUserToDB(List userAnswer) {

        if(verifyDetails(userAnswer)) {
            return CRUD.createUser("System", userAnswer);
        }

        return false;
    }

    private boolean verifyDetails(List userAnswer) {

        boolean valid = true;

        if( userAnswer == null) {
            return false;
        }

        for(int i = 0 ; i < userAnswer.size(); i++) {
            String field = ((Pair)userAnswer.get(i)).getKey();

            if(field.toLowerCase().equals("username")) {
                valid = valid && verifyUsername(((Pair) userAnswer.get(i)).getValue());
            }
            else if(field.toLowerCase().equals("password")) {
                valid = valid && verifyPassword(((Pair) userAnswer.get(i)).getValue(), ((Pair) userAnswer.get(i)).getValue()); //TODO: change to two different password from the user.
            }
            else if(field.toLowerCase().equals("name")) {
                valid = valid && verifyName(((Pair) userAnswer.get(i)).getValue());
            }
        }

        return valid;
    }

    private boolean SetAuthorization(String username, String userType) {
        return CRUD.createAuthorization("System", username, userType);
    }

    /**
     * Initial logger.
     *
     * @param loggerName log name
     * @param printToConsole true if print log to console required.
     * @param isDate true if add to log name the current date.
     *
     * @return Logger
     */
    private static Logger createLogger(String loggerName,Level logLevel, boolean printToConsole, boolean isDate) {

        String fileName = "";
        if (isDate) {
            String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
            fileName = String.format("%s_%s.log", loggerName, date);
        }
        else {
            fileName = String.format("%s.log", loggerName);
        }

        Logger log = Logger.getLogger(String.format("Logs/AMEDY-Logger-%s.log", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime()))); //TODO: initial the logger correctly
        log.setLevel(logLevel);

        FileHandler fh;

        try {
            File directory = new  File("Logs/");
            if(!directory.exists()) {
                directory.mkdir();
            }

            fh = new FileHandler(String.format("Logs/%s", fileName));
            log.addHandler(fh);

            if(!printToConsole) {
                log.setUseParentHandlers(false);
            }

            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

        } catch (IOException e) {
            e.printStackTrace();
        }

    return log;
    }
}