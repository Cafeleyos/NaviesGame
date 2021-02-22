/**
 * @author Camilo Muñoz
 */
import java.util.ArrayList;
public class Board {
    private ArrayList<Navy> navies;
    private final int MIN_LATITUDE=-100;
    private final int MIN_LONGITUDE=-100;
    private final int WIDTH=200;
    private final int HEIGHT=200;

    public Board(ArrayList<Navy> navies) {
        this.navies = navies;
    }

    /**
     * @param name
     * Counts the navies with an specific name
     * @return number of navies
     */

    public int countNavys(String name) {
        int count = 0;
        for (Navy navy:this.navies) {
            if (navy.getName().equals(name))
            count++;
        }
        return count;
    }

    }



