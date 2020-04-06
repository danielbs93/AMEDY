import javafx.util.Pair;

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
    private List<Pair<Season,Budget>> seasonBudget;
    private League league;
    private Stadium homeStadium;
    private List<Match> homeMatches;
    private List<Match> awayMatches;


    public Team(String teamName, List<Player> players, Coach coach, League league, Stadium homeStadium) {
        this.teamName = teamName;

        this.players = players;
        this.coach = coach;
        this.teamOwners = new LinkedList<>();
        this.teamManagers = new LinkedList<TeamManager>();
        this.seasonBudget = new LinkedList<>();
        this.league = league;
        this.homeStadium = homeStadium;
        this.homeMatches = new LinkedList<>();
        this.awayMatches = new LinkedList<>();
    }
}
