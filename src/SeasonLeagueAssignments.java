import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;

public class SeasonLeagueAssignments {
    //Connections
    protected League league;
    protected Season season;
    private MatchAssignmentPolicy myMatchAssignment;
    private LinkedList<Referee> seasonReferees; // allocated by the RFA

    public SeasonLeagueAssignments(League league, Season season, LinkedList<Referee> referees) {
        this.league = league;
        this.season = season;
        this.seasonReferees = referees;
    }

    public MatchAssignmentPolicy getMyMatchAssignment() {
        return myMatchAssignment;
    }

    public void setMyMatchAssignment(MatchAssignmentPolicy myMatchAssignment) {
        this.myMatchAssignment = myMatchAssignment;
    }
}
