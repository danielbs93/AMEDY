public class SystemManager extends User
{


    public SystemManager(String userName, String password, AMEDYSystem AMEDYSystem)
    {
        super(userName, password, AMEDYSystem);

    }

    @Override
    protected String getUserType() {
        return "SystemManager";
    }

    private void closeampermanent(Team team)
    {

    }

    private void removeUser(User user)
    {

    }

    private void showActivities()
    {

    }

    private void buildRecomendSystem()
    {

    }
}
