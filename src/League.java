import javafx.util.Pair;
import java.util.LinkedList;
import java.util.List;

public class League {

    //Fields
    private LeagueRank leagueRank;

    //Connections
    private List<Pair<Season,SeasonLeagueAssignments>> seasonLeague;
    private List<Team> teams;

    public League(LeagueRank leagueRank) {
        this.leagueRank = leagueRank;

        this.seasonLeague = new LinkedList<>();
        this.teams = new LinkedList<>();
    }
}

//ENUM
enum LeagueRank {
    league1,
    league2,
    league3;
}