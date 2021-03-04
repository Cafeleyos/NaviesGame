import java.util.ArrayList;
/**
 * @author Camilo Muñoz
 */

public class Ship {
    private int numberId;
    private ArrayList<Marine> marines;
    private Position location;
    //Score, Shared value with all objects of the class; can be modified with the set method.
    private static int score;
    // CrewRequired,Shared value with all objects of the class; can´t be modified
    private static int crewRequired;

    public Ship(int crewRequired, int id, int latitude, int longitude) {
        this.numberId= id;
        this.marines = new ArrayList<Marine>();
        this.location = new Position();
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        score=0;
        this.crewRequired = crewRequired;
        this.marines = new ArrayList<Marine>();

    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int newScore) {
        score= newScore;
    }

    public static int getCrewRequired() {
        return crewRequired;
    }

    public Position getLocation() {
        return location;
    }

    public void setLocation(Position location) {
        this.location = location;
    }

    public int getNumberId() {
        return numberId;
    }

    public void addMarines(ArrayList<Marine> marine) {
        this.marines= marine;
    }
}
