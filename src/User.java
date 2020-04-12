public abstract class User
{
    private String userName;
    private String password;
    private AMEDYSystem AMEDYSystem;


    public User(String userName, String password, AMEDYSystem AMEDYSystem)
    {
        this.userName = userName;
        this.password = password;
        this.AMEDYSystem = AMEDYSystem;
    }

    protected void logout()
    {
        this.AMEDYSystem.logoutUser(this);
    }

    public String getUserName()
    {
        return this.userName;
    }

    protected abstract String getUserType();
}
