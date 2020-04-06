

import java.time.LocalTime;
import java.util.Date;

public class Event {
    private Date date;
    private LocalTime time;
    private int gameMinute;
    private EventType eventType;

    public Event(Date date, LocalTime time, int gameMinute, EventType eventType) {
        this.date = date;
        this.time = time;
        this.gameMinute = gameMinute;
        this.eventType = eventType;
    }
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