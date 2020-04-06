import java.time.LocalTime;
import java.util.Date;

public class Match {

    //Fields
    private Date date;
    private LocalTime time;
    private Score score;

    //Connections
    private Stadium stadium;
    private Team homeTeam;
    private Team awayTeam;
    private Referee[] referees;
    private EventDiary eventDiary;
    private MatchAssignmentPolicy matchAssignmentPolicy;

    public Match(Date date, LocalTime time, Stadium stadium, Team homeTeam, Team awayTeam, Referee[] referees, EventDiary eventDiary, MatchAssignmentPolicy matchAssignmentPolicy) {
        this.date = date;
        this.time = time;
        this.score = new Score();

        this.stadium = stadium;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.referees = referees;
        this.eventDiary = eventDiary;
        this.matchAssignmentPolicy = matchAssignmentPolicy;
    }
}
