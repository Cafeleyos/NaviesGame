import java.util.ArrayList;

/**
 * @author Camilo Muñoz
 */
public class Navy {
    private String name;
    private Board board;
    private ArrayList<Ship> ships;
    private ArrayList<Marine> marines;
    private ArrayList<Aircraft> aircrafts;
    private ArrayList<AircraftCarrier> carriers;
    // Un código para identificar las flotas (Navy) que no se puede modificar y puede ser consultado por todos.
    public final int IDENTIFICATION;


    public Navy(String name, int identification, Board board) {
        this.IDENTIFICATION= identification;
        this.name = name;
        this.board = board;
        this.carriers= new ArrayList<AircraftCarrier>();
        this.aircrafts = new ArrayList<Aircraft>();
    }


    /**
     *
     * @return number of navies that have the same name.
     */
    public int alias() {
        return (this.board.countNavys(this.name)) -1;
    }
    /**
     * @return number of new aircrafts that could be loaded into the Aircraftcarriers
     */
    public int availabilityOnAricraftCarriers() {
        int counter=0;
        for (AircraftCarrier c: carriers) {
            counter= c.getAvailability() +counter;
        }
        return counter;
    }
    /**
     * Adds an AircraftCarrier to the Navy
     */

    public boolean addAircraftCarrier (AircraftCarrier carrier) {
        boolean result = true;
        for (AircraftCarrier c : carriers) {
            if (c.getNumberID()== carrier.getNumberID()) {
                result = false;
            }
        }
        if (result) {
            carriers.add(carrier);
        }

        return result;
    }
    /**
     * Adds an Aircraft to the Navy
     */
    public boolean addAircraft (Aircraft aircraft) {
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

    public String getName() {
        return this.name;
    }

}
