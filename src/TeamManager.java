public class TeamManager extends UserEventMaker {

    //Connections
    private Team team;

    public TeamManager(String userName, String password, System system, String name, Team team) {
        super(userName, password, system, name);

        this.team = team;
    }
}
