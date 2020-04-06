import java.util.Date;
import java.util.List;

public class Player extends UserEventMaker
{
    //Fields
    private Date birthday;
    private PlayerType playerType;

    //Connections
    private List<Event> event;

    public Player(String userName, String password, System system, String name, Date birthday, PlayerType playerType) {
        super(userName, password, system, name);

        this.birthday = birthday;
        this.playerType = playerType;
    }
}

//enum
enum PlayerType {
    GoalKeeper,
    Defender,
    Midfielders,
    Striker;
}
