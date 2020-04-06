public class Referee extends UserEventMaker
{
    private RefereeType type;

    public Referee(String userName, String password, System system, String name, RefereeType refereeType) {
        super(userName, password, system, name);

        this.type = refereeType;
    }
}

//RefereeType enum
enum RefereeType {
    MainReferee,
    SideReferee;
}