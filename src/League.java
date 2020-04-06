import java.util.LinkedList;
import java.util.List;

public class League {

    //Fields
    private LeagueRank leagueRank;

    //Connections
    private List<Season> seasons;
    private List<Team> teams;
    private List<RepresentativeFootballAssociation> representativeFootballAssociationsList; //TODO: check if we need this

    public League(LeagueRank leagueRank) {
        this.leagueRank = leagueRank;

        this.seasons = new LinkedList<>();;
        this.teams = new LinkedList<>();
        this.representativeFootballAssociationsList = new LinkedList<>(); //TODO:change
    }
}

//ENUM
enum LeagueRank {
    league1,
    league2,
    league3;
}