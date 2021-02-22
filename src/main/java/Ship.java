import java.util.ArrayList;
/**
 * @author Camilo Muñoz
 */

public class Ship {
    private ArrayList<Marine> marines;
    private Position location;

    //Score, Shared value with all objects of the class; can be modified with the set method.
    private static int score;
    // CrewRequired,Shared value with all objects of the class; can´t be modified
    private static int crewRequired;

    public static int getScore() {
        return score;
    }

    public static void setScore(int newScore) {
        score= newScore;
    }

    public static int getCrewRequired() {
        return crewRequired;
    }
}
