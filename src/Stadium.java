import java.util.LinkedList;
import java.util.List;

public class Stadium {

    //Fields
    private String stadiumName;

    //Connections
    private List<Team> teams;
    private List<Match> matches;

    public Stadium(String stadiumName) {
        this.stadiumName = stadiumName;

        this.teams = new LinkedList<>();
        this.matches = new LinkedList<>();
    }
}
