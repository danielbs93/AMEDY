import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.logging.*;
import java.text.SimpleDateFormat;
import java.util.concurrent.ConcurrentHashMap;


public class AMEDYSystem
{
    static Logger systemLogger = createLogger("AMEDY", Level.WARNING, true, true);

    private static Map<String, Integer> premissions = new HashMap<String, Integer>()
    {{
        put("CREATE", 2);
        put("READ", 3);
        put("UPDATE", 5);
        put("DELETE", 7);

//        //CREATE
//        put("CREATE_USER", 3);
//        put("CREATE_TEAM", 5);
//        put("CREATE_LEAGUE", 7);
//        put("CREATE_EVENT", 11);
//        put("CREATE_POLICY", 13);
//        put("CREATE_LEAGUE_RANK", 17);
//        put("CREATE_SEASON", 23);
//        put("CREATE_STADIUM", 29);
//        put("CREATE_MATCH", 31);
//        put("CREATE_BUDGET", 37);
//
//        //READ
//        put("READ_SYSTEM", 41);
//        put("READ_USER", 43);
//
//        //UPDATE
//        put("UPDATE_USER", 47);
//        put("UPDATE_TEAM", 53);
//        put("UPDATE_LEAGUE", 59);
//        put("UPDATE_EVENT", 61);
//        put("UPDATE_POLICY", 67);
//        put("UPDATE_LEAGUE_RANK", 71);
//        put("UPDATE_SEASON", 73);
//        put("UPDATE_STADIUM", 79);
//        put("UPDATE_MATCH", 83);
//        put("UPDATE_BUDGET", 89);
//
//        //DELETE
//        put("DELETE_USER", 97);
//        put("DELETE_TEAM", 101);
//        put("DELETE_LEAGUE", 103);
//        put("DELETE_EVENT", 107);
//        put("DELETE_POLICY", 109);
//        put("DELETE_LEAGUE_RANK", 113);
//        put("DELETE_SEASON", 127);
//        put("DELETE_STADIUM", 131);
//        put("DELETE_MATCH", 137);
//        put("DELETE_BUDGET", 139);
    }};

    private static Map<String, Integer> userPremissions = new HashMap<String, Integer>()
    {{
        put("System", premissions.get("CREATE") * premissions.get("READ") * premissions.get("UPDATE") * premissions.get("DELETE"));

        put("SystemManager", premissions.get("CREATE") * premissions.get("READ") * premissions.get("UPDATE") * premissions.get("DELETE"));
        put("Fan", premissions.get("READ"));
        put("RFA", premissions.get("CREATE") * premissions.get("READ") * premissions.get("UPDATE") * premissions.get("DELETE"));
        put("Referee", premissions.get("CREATE") * premissions.get("READ") * premissions.get("UPDATE") * premissions.get("DELETE"));
        put("Player", premissions.get("READ") * premissions.get("UPDATE"));
        put("Coach", premissions.get("READ"));
        put("TeamOwner", premissions.get("READ") * premissions.get("UPDATE"));
        put("TeamManager", premissions.get("READ") * premissions.get("UPDATE"));
    }};

    private SystemManager systemManeger;
    private ConcurrentHashMap<String ,User> allUsers;
    private Authentication authentication;
    private DataBase database;

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

    public void signUpUser(String userName, String password)
    {

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
        return CRUD.createUser("System", userAnswer);
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