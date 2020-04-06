import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class EventDiary {

    //Fields
    private Date date;

    //Connections
    private Match match;
    private List<Event> events;

    public EventDiary(Date date, Match match) {
        this.date = date;

        this.match = match;
        this.events = new LinkedList<>();
    }
}
