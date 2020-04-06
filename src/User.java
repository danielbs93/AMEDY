import java.util.Observable;

public abstract class User
{
    private String userName;
    private String password;
    private System system;


    public User(String userName, String password, System system)
    {
        this.userName = userName;
        this.password = password;
        this.system = system;
    }

    private void logout()
    {
        this.system.logoutUser(this);
    }


}
