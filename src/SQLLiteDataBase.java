import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.io.FileNotFoundException;

public class SQLLiteDataBase implements DataBase {


    //Fields
    private String path;


    /***
     * Constructor. New DataBase
     */
    public SQLLiteDataBase() throws FileNotFoundException {
        this("AMEDY.db");
    }


    /***
     * Constructor.
     *
     * @param DBPath path to DB.
     * @throws FileNotFoundException if file not exists.
     */
    public SQLLiteDataBase(String DBPath) throws FileNotFoundException {

        File DBFile = new File(DBPath);

        if(DBFile.exists()) {
          this.path = DBPath;
        }
        else {
            throw new FileNotFoundException("failed to connect the DB");
        }
    }

    @Override
    public boolean checkConnection() {

        Connection conn = null;

        try {
            // db parameters
            String url = String.format("jdbc:sqlite:%s", this.path);

            // create a connection to the database
            conn = DriverManager.getConnection(url);

        } catch (SQLException e) {

//            System.out.println(e.getMessage());
            return false;

        } finally {

            try {
                if (conn != null) {

                    conn.close();

                    return true;
                }

            } catch (SQLException ex) {

//                System.out.println(ex.getMessage());
                return false;
            }
        }

        return false;
    }

    @Override
    public Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(String.format("jdbc:sqlite:%s", this.path));

        } catch (SQLException e) {
            AMEDYSystem.systemLogger.warning("could't achieve connection");
            return null;

        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
            AMEDYSystem.systemLogger.warning("could't achieve connection");
            return null;
        }
    }
}
