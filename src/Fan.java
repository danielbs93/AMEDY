import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class Fan extends User {

    //Fields
    private String name;
    private List<Observable> following;

    public Fan(String userName, String password, AMEDYSystem AMEDYSystem, String name) {
        super(userName, password, AMEDYSystem);

        this.name = name;
        this.following = new LinkedList<>();
    }


    public void update(UserEventMaker o, Object arg) {
    }
}
