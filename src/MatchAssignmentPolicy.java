import java.util.LinkedList;
import java.util.List;

public class MatchAssignmentPolicy {

    //Fields

    //Connections
    private RepresentativeFootballAssociation rfa;
    private List<Match> matches; //TODO: we dont need this

    public MatchAssignmentPolicy(RepresentativeFootballAssociation rfa) {
        this.rfa = rfa;
        this.matches = new LinkedList<>();
    }
}
