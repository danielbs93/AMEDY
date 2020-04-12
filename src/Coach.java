public class Coach extends UserEventMaker
{
    private String qualification;

    public Coach(String userName, String password, AMEDYSystem AMEDYSystem, String name, String qualification) {
        super(userName, password, AMEDYSystem, name);

        this.qualification = qualification;
    }

    @Override
    protected String getUserType() {
        return "Coach";
    }
}
