import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;

public class Season {

    //Fields
    private int year;

    //Connections
    private AMEDYSystem AMEDYSystem;
    private LeagueRankPolicy leagueRankPolicy;
    private List<Pair<Team, Budget>> teamBudget;
    private List<Pair<League, SeasonLeagueAssignments>> leagueSeason;

    public Season(int year, AMEDYSystem AMEDYSystem, LeagueRankPolicy leagueRankPolicy) {
        this.year = year;
        this.AMEDYSystem = AMEDYSystem;
        this.leagueRankPolicy = leagueRankPolicy;
        this.teamBudget = new LinkedList<>();
        leagueSeason = new LinkedList<>();
    }


    //TODO: assigning matches after using MatchAssigningPolicy
}
