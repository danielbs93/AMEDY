import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;

public class Season {

    //Fields
    private int year;

    //Connections
    private System system;
    private LeagueRankPolicy leagueRankPolicy;
    private League league;
    private List<Pair<Team, Budget>> teamBudget;

    public Season(int year, System system, LeagueRankPolicy leagueRankPolicy, League league) {
        this.year = year;

        this.system = system;
        this.leagueRankPolicy = leagueRankPolicy;
        this.league = league;
        this.teamBudget = new LinkedList<>();
    }
}
