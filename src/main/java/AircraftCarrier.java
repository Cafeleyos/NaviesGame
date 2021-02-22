import java.util.ArrayList;
/**
 * @author Camilo Muñoz
 */
public class AircraftCarrier {
    private int numberID;
    private int capacity;
    private ArrayList<Marine> marines;
    private Position location;
    private ArrayList<Aircraft> aircrafts;
    private Marine captain;
    // Score, Shared value with all objects of the class; can be modified with the set method.
    private static int score;
    // CrewRequired,Shared value with all objects of the class; can´t be modified
    private static int crewRequired;


    public AircraftCarrier(int numberID, int capacity) {
        this.numberID = numberID;
        this.capacity = capacity;
        this.aircrafts = new ArrayList<Aircraft>();

    }

    /**
     * @param aircraft
     * Adds an Aircraft to an specific AircraftCarrier
     */

    public boolean addAircraftToCarrier (Aircraft aircraft) {
        boolean result = true;
        for (Aircraft a : aircrafts) {
            if (a.getLicencePlate()== aircraft.getLicencePlate()) {
                result = false;
            }
        }
        if (result) {
            aircrafts.add(aircraft);
        }
        return result;
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





}