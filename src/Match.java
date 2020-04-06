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

    public Match(Date date, LocalTime time, Stadium stadium, Team homeTeam, Team awayTeam, Referee[] referees) {
        this.date = date;
        this.time = time;
        this.score = new Score();

        this.stadium = stadium;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.referees = referees; //TODO:check if 4
        this.eventDiary = new EventDiary(date,this);
    }
}
