public class Coach extends UserEventMaker
{
    private String qualification;

    public Coach(String userName, String password, System system, String name, String qualification) {
        super(userName, password, system, name);

        this.qualification = qualification;
    }
}
