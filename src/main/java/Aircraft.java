/**
 * Class that represent an aircraft. It must belong to an AircraftCarrier to exist
 * @author Camilo Muñoz
 */
public class Aircraft {

    private String licencePlate;
    private Boolean inAir;
    private Marine copilot;
    private Marine pilot;
    private Position location;
    private AircraftCarrier carrier;

    // Score, Shared value with all objects of the class; can be modified with the set method.
    private static int score;
    // CrewRequired,Shared value with all objects of the class; can´t be modified
    private static int crewRequired;

    public Aircraft(String licencePlate, AircraftCarrier carrier,int latitude, int longitude) {
        this.licencePlate=licencePlate;
        this.carrier = carrier;
        this.inAir = false;
        this.location = new Position();
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        this.copilot = new Marine(null,0);
        this.pilot = new Marine(null,0);


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

    public String getLicencePlate() {
        return licencePlate;
    }

    public Boolean getInAir() {
        return inAir;
    }

    public void setInAir(Boolean inAir) {
        this.inAir = inAir;
    }

    public AircraftCarrier getCarrier() {
        return carrier;
    }

    public Position getLocation() {
        return location;
    }

    public void setLocation(Position location) {
        this.location = location;
    }

    /**
     * Adds a marine to the aircraft
     * @param marine that will be the pilot
     */
    public void addPilot(Marine marine) {
        this.pilot= marine;
    }
    /**
     * Adds a marine to the aircraft
     * @param marine that will be the copilot
     */
    public void addCoPilot(Marine marine) {
        this.copilot= marine;
    }

}
