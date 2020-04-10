public class TeamManager extends UserEventMaker {

    //Connections
    private Team team;

    public TeamManager(String userName, String password, AMEDYSystem AMEDYSystem, String name, Team team) {
        super(userName, password, AMEDYSystem, name);

        this.team = team;
    }
}
