public class TeamOwner extends UserEventMaker {

    //Connections
    private Team team;

    public TeamOwner(String userName, String password, AMEDYSystem AMEDYSystem, String name, Team team) {
        super(userName, password, AMEDYSystem, name);

        this.team = team;
    }

    @Override
    protected String getUserType() {
        return "TeamOwner";
    }
}
