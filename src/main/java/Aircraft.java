/**
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

    public Aircraft(String licencePlate, AircraftCarrier carrier) {
        this.licencePlate=licencePlate;
        this.carrier = carrier;
    }


    public static int getScore() {
        return score;
    }
    /**
     * @param newScore
     * Changes the actual score
     */
    public static void setScore(int newScore) {
        score= newScore;
    }

    public static int getCrewRequired() {
        return crewRequired;
    }

    public String getLicencePlate() {
        return licencePlate;
    }
}
