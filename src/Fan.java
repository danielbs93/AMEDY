import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Fan extends User {

    //Fields
    private String name;
    private List<Observable> following;

    public Fan(String userName, String password, System system, String name) {
        super(userName, password, system);

        this.name = name;
        this.following = new LinkedList<>();
    }


    public void update(UserEventMaker o, Object arg) {

    }
}
