import java.net.ConnectException;
import java.io.FileNotFoundException;


public class main {

    public static void main(String[] args) {

        try {

            //DataBase
            DataBase DB = new SQLLiteDataBase();
            new CRUD(DB);

            //Achieve Authentication
            Authentication aut = new Authentication(DB);

            AMEDYSystem sys = new AMEDYSystem(aut);
            sys.boot();

        } catch (FileNotFoundException validExc) {
            System.out.println(validExc.getMessage());

        } catch (ConnectException conExc) {
            System.out.println("test");
        }
    }
}
