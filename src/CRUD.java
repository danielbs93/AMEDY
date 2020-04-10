import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class CRUD {

    //Fields
    private static DataBase database = null;

    /***
     * Constructor.
     *
     * @param database database to verify all CRUD operations.
     */
    public CRUD(DataBase database) {
        if(CRUD.database == null) {
            this.database = database;
        }
    }

    //CREATE
    /***
     *
     *
     * @param askBy who is ask to do this CREATE TABLE operation, for permission.
     * @param createStmt query to send that database include the whole query.
     */
    public static void createTable(String askBy, List<String> createStmt) {

        List<String> ableToAddUser = new ArrayList<String>() {{
            add("System");
        }};

        if(ableToAddUser.contains(askBy)) {
            for(String stmt : createStmt) {
                insertQuery(stmt, new ArrayList());
            }
        }
    }

    /***
     *
     * @param askBy who is ask to do this Operation, for verify permission.
     * @param username username to insert the database.
     * @param userType type of the authorization for this given user.
     *
     * @return true if the create succeeded, false otherwise.
     */
    public static boolean createAuthorization(String askBy, String username, String userType) {

        List<String> ableToAddUser = new ArrayList<String>() {{
            add("System");
            add("SystemManager");
            add("RFA");
        }};

        if(ableToAddUser.contains(askBy)) {
            List<Pair> arguments = new ArrayList<>();

            arguments.add(new Pair("username", username));
            arguments.add(new Pair("permissionLevel", userType));

            return insertQuery("Authorization", arguments);
        }

        return false;
    }

    /**
     * Create new user.
     *
     * @param askBy who is ask to do this Operation, for verify permission.
     * @param userAnswer list of all user details.
     *
     * @return true if the create succeeded, false otherwise.
     */
    public static boolean createUser(String askBy, List userAnswer) {

        List<String> ableToAddUser = new ArrayList<String>() {{
            add("System");
        }};

        if(ableToAddUser.contains(askBy)) {

            return insertQuery("Users", userAnswer);
        }

        return false;
    }

    //READ
    /***
     *
     */
    public static ResultSet select(String askBy, String table, List params) {

        List<String> ableToAddUser = new ArrayList<String>() {{
            add("System");
            add("SystemManager");
        }};

        if(ableToAddUser.contains(askBy)) {

            return (ResultSet)selectQuery(table, params);
        }

        return null;
    }

    //CREATE
    /***
     * preform query by given the query kind and the query parameters.
     *
     * @param table name of the relevent table, if there is not relevant table, it will be use for the full query.
     * @param params list of Pairs, each pair contain the field as a key and the parameter.
     *
     * @return true if the create succeeded, false otherwise.
     */
    private static boolean insertQuery(String table, List params) {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try{
            //STEP 3: Open a connection
            AMEDYSystem.systemLogger.info("Connecting to a selected database...");
            conn = CRUD.database.getConnection();
            AMEDYSystem.systemLogger.info("Connected database successfully...");

            //STEP 4: Execute a query
            AMEDYSystem.systemLogger.info("Inserting records into the table...");
            String query = "";

            String firstRow = String.format("INSERT INTO %s(", table);
            String secondRow = " VALUES (";

            for(int i = 0 ; i < params.size(); i++) {
                Pair p = (Pair) params.get(i);
                if(i + 1 < params.size() ) {
                    firstRow += p.getKey() + ", ";
                    secondRow += String.format("'%s', ", p.getValue());
                }
                else {
                    firstRow += p.getKey() + ")";
                    secondRow += String.format("'%s')", p.getValue());
                }
            }

            query = firstRow + secondRow;

            pstmt = conn.prepareStatement(query);
            pstmt.executeUpdate();
        }
        catch(SQLException se){
            //Handle errors for JDBC
//            se.printStackTrace();
            AMEDYSystem.systemLogger.warning(se.toString());

        }catch(Exception e){
            //Handle errors for Class.forName
//            e.printStackTrace();
            AMEDYSystem.systemLogger.warning(e.toString());

        }finally {
            //finally block used to close resources
            try {
                if (pstmt != null) {

                    conn.close();
                    return true;
                }

            } catch (SQLException se) {
//                se.printStackTrace();
                AMEDYSystem.systemLogger.warning(se.toString());
                return false;
            }// do nothing
            try {
                if (conn != null) {

                    conn.close();
                    return false;
                }
            } catch (SQLException se) {

//                se.printStackTrace();
                AMEDYSystem.systemLogger.warning(se.toString());
                return false;
            }
        }
        return false;
    }

    //SELECT
    private static Object selectQuery(String table, List params) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet result = null;

        try{
            //STEP 3: Open a connection
            AMEDYSystem.systemLogger.info("Connecting to a selected database...");
            conn = CRUD.database.getConnection();
            AMEDYSystem.systemLogger.info("Connected database successfully...");

            //STEP 4: Execute a query
            AMEDYSystem.systemLogger.info("Inserting records into the table...");
            String query = "";

            stmt = conn.createStatement();

            String firstRow = "";
            String secondRow = " FROM " + table;

            if(params != null) {

                firstRow = "SELECT (";

                for(int i = 0 ; i < params.size(); i++) {

                    String str = (String)params.get(i);

                    if(i + 1 < params.size() ) {
                        firstRow += str + ", ";
                    }
                    else {
                        firstRow += str + ")";
                    }
                }
            }
            else {
                firstRow = "SELECT *";
            }

            query = firstRow + secondRow;

            //execute
            result = stmt.executeQuery(query);
        }
        catch(SQLException se){
            //Handle errors for JDBC
//            se.printStackTrace();
            AMEDYSystem.systemLogger.warning(se.toString());

        }catch(Exception e){
            //Handle errors for Class.forName
//            e.printStackTrace();
            AMEDYSystem.systemLogger.warning(e.toString());

        }finally {
            //finally block used to close resources
            try {
                if (stmt != null) {

                    conn.close();
                    return result;
                }

            } catch (SQLException se) {
//                se.printStackTrace();
                AMEDYSystem.systemLogger.warning(se.toString());
                return null;
            }// do nothing
            try {
                if (conn != null) {

                    conn.close();
                    return null;
                }
            } catch (SQLException se) {

//                se.printStackTrace();
                AMEDYSystem.systemLogger.warning(se.toString());
                return null;
            }
        }
        return null;
    }

}
