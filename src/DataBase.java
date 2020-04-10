import java.sql.Connection;

public interface DataBase {

    /***
     * Check DB availability.
     *
     * @return True if the DB is available, False otherwise.
     */
    boolean checkConnection();

    /***
     * Get connection from the database.
     *
     * @return Connection, null if connection attempt fail.
     */
    Connection getConnection();
}
