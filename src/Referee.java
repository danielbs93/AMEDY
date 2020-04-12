public class Referee extends UserEventMaker
{
    private RefereeType type;

    public Referee(String userName, String password, AMEDYSystem AMEDYSystem, String name, RefereeType refereeType) {
        super(userName, password, AMEDYSystem, name);

        this.type = refereeType;
    }

    @Override
    protected String getUserType() {
        return "Referee";
    }
}

//RefereeType enum
enum RefereeType {
    MainReferee,
    SideReferee;
}