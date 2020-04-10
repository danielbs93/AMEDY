import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Player extends UserEventMaker
{
    //Fields
    private Date birthday;
    private PlayerType playerType;

    //Connections
    private List<Event> events;

    public Player(String userName, String password, AMEDYSystem AMEDYSystem, String name, Date birthday, PlayerType playerType) {
        super(userName, password, AMEDYSystem, name);

        this.birthday = birthday;
        this.playerType = playerType;
        this.events = new LinkedList<>();
    }
}

//enum
enum PlayerType {
    GoalKeeper,
    Defender,
    Midfielders,
    Striker;
}
