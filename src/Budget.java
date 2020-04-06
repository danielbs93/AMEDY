public class Budget {

    //Fields
    private double amount;

    //Connections
    private Season season;
    private Team team;

    public Budget(double amount, Season season, Team team) {
        this.amount = amount;

        this.season = season;
        this.team = team;
    }
}
