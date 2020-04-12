import java.net.ConnectException;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class main {

    public static void main(String[] args) {

        AMEDYSystem sys = null;
        try {

            //DataBase
            DataBase DB = new SQLLiteDataBase();
            new CRUD(DB);

            //Achieve Authentication
            Authentication aut = new Authentication(DB);

            sys = new AMEDYSystem(aut);
            sys.boot();

        } catch (FileNotFoundException validExc) {
            System.out.println(validExc.getMessage());

        } catch (ConnectException conExc) {
            System.out.println("test");
        }


        System.out.println("---------------------------");
        System.out.println("Welcome to AMEDY system.");
        System.out.println("---------------------------\n");
        User currentUser = null;
        while(true)
        {

            if (currentUser == null) //Guest
            {
                currentUser = moveToGuestMenu(sys);
            }
            else if (currentUser instanceof Fan)//Fan
            {
                currentUser = moveToFanMenu((Fan)currentUser);
            }
            else if(currentUser instanceof SystemManager) //SystemManager
            {
               currentUser = moveToSystemManagerMenu((SystemManager)currentUser);
            }
            else if(currentUser instanceof RepresentativeFootballAssociation)//RFA
            {
                currentUser = moveToRFAmenu((RepresentativeFootballAssociation)currentUser);
            }
            else if(currentUser instanceof Referee)//Referee
            {
                currentUser = moveToRefereeMenu((Referee)currentUser);
            }
            else if(currentUser instanceof Player)//Player
            {
                currentUser = moveToPlayerMenu((Player)currentUser);
            }
            else if(currentUser instanceof Coach)//Coach
            {
                currentUser = moveToCoachMenu((Coach)currentUser);
            }
            else if(currentUser instanceof TeamOwner)//TeamOwner
            {
                currentUser = moveToTeamOwnerMenu((TeamOwner)currentUser);
            }
            else if(currentUser instanceof TeamManager)//TeamManager
            {
                currentUser = moveToTeamManagerMenu((TeamManager)currentUser);
            }

        }
    }



    /**
     * Guest menu options. conatins all the options a Guest can do in the system.
     * @param sys - AMEDYSystem instance for
     * @return
     */
    private static User moveToGuestMenu(AMEDYSystem sys)
    {
        User currentUser = null;
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            System.out.println("you are now logged in as a Guest.");
            System.out.println("choose one of the next options:");
            System.out.println("" +
                    "1. sign up to the system\n" +
                    "2. sign in to the system\n" +
                    "3. search" +
                    "");

            int caseIndex = sc.nextInt();
            while(caseIndex < 1 || caseIndex > 3)
            {
                System.out.println("no such options in menu.\n" +
                        "try again and choose valid option:");
                caseIndex = sc.nextInt();
            }

            switch (caseIndex)
            {

                case 1:
                    currentUser = sys.signup();
                    return currentUser;
                    //no need break because of the return.
                case 2:
                    currentUser = sys.signin();
                    return currentUser;
                    //no need break because of the return.

                case 3:
                    //TODO: implement search option inaal shurlak.
                    break;

                case 4:
                    System.out.println("");
                    break;
            }//end of switch case
        }
    }

    /**
     * Fan menu options. contains all the options a fan can do in the system.
     * @param fanUser - Fan User instance.
     * @return null - when fan want to disconnect.
     */
    private static User moveToFanMenu(Fan fanUser)
    {
        Scanner sc = new Scanner(System.in);

        while(true)
        {
            System.out.println("you are now logged in as " + fanUser.getUserName() +" (Fan).");
            System.out.println("choose one of the next options:");
            System.out.println("" +
                    "1. follow player.\n" +
                    "2. follow game.\n" +
                    "3. watch history.\n" +
                    "4. search.\n" +
                    "5. fill complaint.\n" +
                    "6. view my personal info.\n" +
                    "7. edit my personal info.\n" +
                    "8. logout.");

            int caseIndex = sc.nextInt();
            while(caseIndex < 1 || caseIndex > 8)
            {
                System.out.println("no such options in menu.\n" +
                        "try again and choose valid option:");
                caseIndex = sc.nextInt();
            }

            switch (caseIndex)
            {
                case 1:
//                    fanUser.followPlayer() //TODO: to implement
                    break;

                case 2:
//                    fanUser.followGame() //TODO: to implement.
                    break;

                case 3:
//                    fanUser.watchHistory(); //TODO: to implement.
                    break;

                case 4:
//                    fanUser.search() //TODO: to implement.
                    System.out.println("");
                    break;

                case 5:
//                    fanUser.fillComplaint(); //TODO: to implement.
                    break;

                case 6:
//                    fanUser.viewMyPersonalInfo(); //TODO: to implement.
                    break;
                case 7:
//                    fanUser.editMyPersonalInfo(); //TODO: to implement.
                    break;
                case 8:
//                    fanUser.logout(); //TODO: to implement
                    return null;
                //no need break here because of the return.
            }//end of switch case
        }
    }

    /**
     *System Manager menu options. contains all the options a System Manager can do in the system.
     * @param systemManager - SystemManager User instance.
     * @return null - when User want to disconnect.
     */
    private static User moveToSystemManagerMenu(SystemManager systemManager)
    {
        Scanner sc = new Scanner(System.in);

        while(true)
        {
            System.out.println("you are now logged in as " + systemManager.getUserName() +" (System Manager).");
            System.out.println("choose one of the next options:");
            System.out.println("" +
                    "1. close team permanent.\n" +
                    "2. remove user.\n" +
                    "3. show activities.\n" +
                    "4. build activities.\n" +
                    "5. logout.");

            int caseIndex = sc.nextInt();
            while(caseIndex < 1 || caseIndex > 5)
            {
                System.out.println("no such option in menu.\n" +
                        "try again and choose valid option:");
                caseIndex = sc.nextInt();
            }

            switch (caseIndex)
            {
                case 1:
//                    systemManager.closeTeamPermanent(); //TODO: to implement
                    break;

                case 2:
//                    systemManager.removeUser(); //TODO: to implement.
                    break;

                case 3:
//                    systemManager.showActivities(); //TODO: to implement.
                    break;

                case 4:
//                    systemManager.buildRecommendSystem() //TODO: to implement.
                    System.out.println("");
                    break;

                case 5:
//                    systemManager.logout(); //TODO: to implement.
                    return null;
                //no need break here because of the return.

            }//end of switch case
        }
    }

    /**
     *Representative Football Association(RFA) menu options. contains all the options a RFA can do in the system.
     * @param RFA - Representative Football Association User instance.
     * @return null - when User want to disconnect.
     */
    private static User moveToRFAmenu(RepresentativeFootballAssociation RFA)
    {
        Scanner sc = new Scanner(System.in);

        while(true)
        {
            System.out.println("you are now logged in as " + RFA.getUserName() +" (RFA).");
            System.out.println("choose one of the next options:");
            System.out.println("" +
                    "1. create new league.\n" +
                    "2. add season to league.\n" +
                    "3. registrationReferee.\n" +
                    "4. remove user.\n" +
                    "5. update policy.\n" +
                    "6. edit my personal info.\n" +
                    "7. log out.");

            int caseIndex = sc.nextInt();
            while(caseIndex < 1 || caseIndex > 7)
            {
                System.out.println("no such option in menu.\n" +
                        "try again and choose valid option:");
                caseIndex = sc.nextInt();
            }

            switch (caseIndex)
            {
                case 1:
//                    RFA.createNewLeague() //TODO: to implement
                    break;

                case 2:
//                    RFA.addSeasonToLeague() //TODO: to implement.
                    break;

                case 3:
//                    RFA.registrationReferee(); //TODO: to implement.
                    break;

                case 4:
//                    RFA.removeUser() //TODO: to implement.
                    System.out.println("");
                    break;

                case 5:
//                    RFA.removePolicy(); //TODO: to implement.
                    break;

                case 6:
//                    RFA.editMyPersonalInfo(); //TODO: to implement.
                    break;

                case 7:
                    RFA.logout();
                    return null;
                //no need break here because of the return.

            }//end of switch case
        }
    }

    /**
     *Referee menu options. contains all the options a referee can do in the system.
     * @param refereeUser - Referee User instance.
     * @return null - when User want to disconnect.
     */
    private static User moveToRefereeMenu(Referee refereeUser)
    {
        Scanner sc = new Scanner(System.in);

        while(true)
        {
            System.out.println("you are now logged in as " + refereeUser.getUserName() +" (Referee).");
            System.out.println("choose one of the next options:");
            System.out.println("" +
                    "1. add event to match.\n" +
                    "2. update event to match.\n" +
                    "3. edit my personal info.\n" +
                    "4. logout.");

            int caseIndex = sc.nextInt();
            while(caseIndex < 1 || caseIndex > 4)
            {
                System.out.println("no such option in menu.\n" +
                        "try again and choose valid option:");
                caseIndex = sc.nextInt();
            }

            switch (caseIndex)
            {
                case 1:
//                    refereeUser.addEventToMatch() //TODO: to implement
                    break;

                case 2:
//                    refereeUser.updateEventToMatch() //TODO: to implement.
                    break;

                case 3:
//                    refereeUser.editMyPersonalInfo(); //TODO: to implement.
                    break;

                case 4:
//                    refereeUser.logout() //TODO: to implement.
                    return null;
                //no need break here because of the return.

            }//end of switch case
        }
    }

    /**
     *Player menu options. contains all the options a player can do in the system.
     * @param player - Player User instance.
     * @return null - when User want to disconnect.
     */
    private static User moveToPlayerMenu(Player player)
    {
        Scanner sc = new Scanner(System.in);

        while(true)
        {
            System.out.println("you are now logged in as " + player.getUserName() +" (Player).");
            System.out.println("choose one of the next options:");
            System.out.println("" +
                    "1. upload new content.\n" +
                    "2. edit my personal info.\n" +
                    "3. logout.");

            int caseIndex = sc.nextInt();
            while(caseIndex < 1 || caseIndex > 3)
            {
                System.out.println("no such option in menu.\n" +
                        "try again and choose valid option:");
                caseIndex = sc.nextInt();
            }

            switch (caseIndex)
            {
                case 1:
//                    player.uploadNewContent() //TODO: to implement
                    break;

                case 2:
//                    player.editMyPersonalInfo() //TODO: to implement.
                    break;

                case 3:
//                    player.logout(); //TODO: to implement.
                    return null;
                //no need break here because of the return.

            }//end of switch case
        }
    }


    /**
     *Coach menu options. contains all the options a Coach can do in the system.
     * @param coach - Coach User instance.
     * @return null - when User want to disconnect.
     */
    private static User moveToCoachMenu(Coach coach)
    {
        Scanner sc = new Scanner(System.in);

        while(true)
        {
            System.out.println("you are now logged in as " + coach.getUserName() +" (Coach).");
            System.out.println("choose one of the next options:");
            System.out.println("" +
                    "1. upload new content.\n" +
                    "2. edit my personal info.\n" +
                    "3. logout.");

            int caseIndex = sc.nextInt();
            while(caseIndex < 1 || caseIndex > 3)
            {
                System.out.println("no such option in menu.\n" +
                        "try again and choose valid option:");
                caseIndex = sc.nextInt();
            }

            switch (caseIndex)
            {
                case 1:
//                    coach.uploadNewContent() //TODO: to implement
                    break;

                case 2:
//                    coach.editMyPersonalInfo() //TODO: to implement.
                    break;

                case 3:
//                    coach.logout(); //TODO: to implement.
                    return null;
                //no need break here because of the return.

            }//end of switch case
        }
    }


    /**
     *TeamOwner menu options. contains all the options a TeamOwner can do in the system.
     * @param teamOwner - TeamOwner User instance.
     * @return null - when User want to disconnect.
     */
    private static User moveToTeamOwnerMenu(TeamOwner teamOwner)
    {
        Scanner sc = new Scanner(System.in);

        while(true)
        {
            System.out.println("you are now logged in as " + teamOwner.getUserName() +" (TeamOwner).");
            System.out.println("choose one of the next options:");
            System.out.println("" +
                    "1. add assert.\n" +
                    "2. add new team owner.\n" +
                    "3. add new team manager.\n" +
                    "4. close team.\n" +
                    "5. new finincial report.\n" +
                    "6. edit my personal info.\n" +
                    "7. log out.");

            int caseIndex = sc.nextInt();
            while(caseIndex < 1 || caseIndex > 7)
            {
                System.out.println("no such option in menu.\n" +
                        "try again and choose valid option:");
                caseIndex = sc.nextInt();
            }

            switch (caseIndex)
            {
                case 1:
//                    teamOwner.addAssert() //TODO: to implement
                    break;

                case 2:
//                    teamOwner.newTeamOwner() //TODO: to implement.
                    break;

                case 3:
//                    teamOwner.newTeamManager(); //TODO: to implement.
                    break;

                case 4:
//                    teamOwner.closeTeam() //TODO: to implement.
                    System.out.println("");
                    break;

                case 5:
//                    teamOwner.newFinancialReport(); //TODO: to implement.
                    break;

                case 6:
//                    teamOwner.editMyPersonalInfo(); //TODO: to implement.
                    break;

                case 7:
                    teamOwner.logout();
                    return null;
                //no need break here because of the return.

            }//end of switch case
        }
    }


    /**
     *TeamManager menu options. contains all the options a TeamManager can do in the system.
     * @param teamManager - TeamManager User instance.
     * @return null - when User want to disconnect.
     */
    private static User moveToTeamManagerMenu(TeamManager teamManager)
    {
        Scanner sc = new Scanner(System.in);

        while(true)
        {
            System.out.println("you are now logged in as " + teamManager.getUserName() +" (TeamManager).");
            System.out.println("choose one of the next options:");
            System.out.println("" +
                    "1. upload new content.\n" +
                    "2. edit my personal info.\n" +
                    "3. logout.");

            int caseIndex = sc.nextInt();
            while(caseIndex < 1 || caseIndex > 3)
            {
                System.out.println("no such option in menu.\n" +
                        "try again and choose valid option:");
                caseIndex = sc.nextInt();
            }

            switch (caseIndex)
            {
                case 1:
//                    teamManager.editMyPersonalInfo() //TODO: to implement
                    break;

                case 2:
//                    teamManager.editMyPersonalInfo(); //TODO: to implement.
                    break;
                case 3:
                    teamManager.logout(); //TODO: to implement.
                    return null;
                //no need break here because of the return.

            }//end of switch case
        }
    }
}
