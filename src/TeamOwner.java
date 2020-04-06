public class TeamOwner extends UserEventMaker {

    //Connections
    private Team team;

    public TeamOwner(String userName, String password, System system, String name, Team team) {
        super(userName, password, system, name);

        this.team = team;
    }
}
