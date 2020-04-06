import java.sql.Time;
import java.util.Date;

public class Event {
    private Date date;
    private Time time;
    private int gameMinute;
    private EventType eventType;

}
//ENUM
enum EventType {
    Foul,
    Injury,
    Substitution,
    Goal,
    Offside,
    YellowCard,
    RedCard;
}