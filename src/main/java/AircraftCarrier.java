import java.util.ArrayList;
/**
 * Class that represent an AircraftCarrier.
 * @author Camilo Muñoz
 */
public class AircraftCarrier {
    private int numberID;
    private int capacity;
    private ArrayList<Marine> marines;
    private Position location;
    private ArrayList<Aircraft> aircrafts;
    // Score, Shared value with all objects of the class; can be modified with the set method.
    private static int score;
    // CrewRequired,Shared value with all objects of the class; can´t be modified
    private static int crewRequired;



    public AircraftCarrier(int numberID, int capacity, int latitude, int longitude) {
        this.numberID = numberID;
        this.capacity = capacity;
        this.aircrafts = new ArrayList<Aircraft>();
        this.location = new Position();
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        this.marines = new ArrayList<Marine>();
    }

    /**
     * @param aircraft
     * Adds an Aircraft to an specific AircraftCarrier
     */

    public void addAircraftToCarrier (Aircraft aircraft) {
        aircrafts.add(aircraft);
    }

    /**
     *
     * @return number of new aircrafts that could be loaded into the Aircraftcarrier
     */

    public int getAvailability() {
        return capacity-aircrafts.size();
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int newScore) {
        score = newScore;
    }

    public static int getCrewRequired() {
        return crewRequired;
    }

    public int getNumberID() {
        return this.numberID;
    }

    public int getCapacity() {
        return capacity;
    }

    public Position getLocation() {
        return location;
    }

    public void setLocation(Position location) {
        this.location = location;
    }

    /**
     * Add an amount of marines that will control the AircraftCarrier.
     * @param marine
     */

    public void addMarines(ArrayList<Marine> marine) {
        this.marines= marine;
    }
}