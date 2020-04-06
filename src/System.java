import java.util.concurrent.ConcurrentHashMap;

public class System
{

//    public static Logger systemLogger = new Logger(); //TODO: initial the logger correctly

    private SystemManager systemManeger;
    private ConcurrentHashMap<String ,User> allUsers;
    private Authentication authentication;

    public System(Authentication authentication) {
        this.authentication = authentication;
    }

    public void boot()
    {
        //TODO: initial SystemManager
    }
    public void logoutUser(User user)
    {

    }

    public void signUpUser(String userName, String password)
    {

    }

    private void connectToExternalSystem()
    {

    }
}