import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;

public interface MatchAssignmentPolicy {

     LinkedList<Pair<Team,Team>> assginMatches(LinkedList<Team> allTeams);


}
