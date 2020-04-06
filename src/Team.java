import java.util.LinkedList;
import java.util.List;

public class Team {

    //Fields
    private String teamName;

    //Connections
    private List<Player> players;
    private Coach coach;
    private List<TeamOwner> teamOwners;
    private List<TeamManager> teamManagers;
    private List<Season> seasons;
    private League league;
    private Stadium homeStadium;
    private List<Match> homeMatches;
    private List<Match> awayMatches;


    public Team(String teamName, List<Player> players, Coach coach, List<TeamOwner> teamOwners, League league, Stadium homeStadium) {
        this.teamName = teamName;

        this.players = players;
        this.coach = coach;
        this.teamOwners = teamOwners;
        this.teamManagers = new LinkedList<TeamManager>();
        this.seasons = new LinkedList<Season>();
        this.league = league;
        this.homeStadium = homeStadium;
        this.homeMatches = new LinkedList<>();
        this.awayMatches = new LinkedList<>();
    }
}
