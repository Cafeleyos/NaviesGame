import java.util.ArrayList;
import java.util.List;

/**
 * @author Camilo Muñoz
 */
public class Navy {
    private String name;
    private Board board;
    private List<Ship> ships;
    private List<Marine> marines;
    private List<Aircraft> aircrafts;
    private List<AircraftCarrier> carriers;
    // Un código para identificar las flotas (Navy) que no se puede modificar y puede ser consultado por todos.
    public final int IDENTIFICATION;


    public Navy(String name, int identification, Board board) {
        this.IDENTIFICATION = identification;
        this.name = name;
        this.board = board;
        this.carriers = new ArrayList<AircraftCarrier>();
        this.aircrafts = new ArrayList<Aircraft>();
        this.ships = new ArrayList<Ship>();
        this.marines = new ArrayList<Marine>();
    }


    /**
     * @return number of navies that have the same name.
     */
    public int alias() {
        return (this.board.countNavys(this.name)) - 1;
    }

    /**
     * @return number of new aircrafts that could be loaded into the Aircraftcarriers
     */
    public int availabilityOnAricraftCarriers() {
        int counter = 0;
        for (AircraftCarrier c : carriers) {
            counter = c.getAvailability() + counter;
        }
        return counter;
    }

    /**
     * Adds an AircraftCarrier to the Navy
     */

    public boolean addAircraftCarrier(AircraftCarrier carrier) {
        boolean result = true;
        for (AircraftCarrier c : carriers) {
            if (c.getNumberID() == carrier.getNumberID()) {
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
    public boolean addAircraft(Aircraft aircraft) {
        boolean result = true;
        if (aircraft.getCarrier().getAvailability() > 0) {


            for (Aircraft a : aircrafts) {
                if (a.getLicencePlate() == aircraft.getLicencePlate()) {
                    result = false;
                }
            }
            if (result) {
                aircrafts.add(aircraft);
                aircraft.getCarrier().addAircraftToCarrier(aircraft);
            }
            return result;
        } return false;


    }

    public boolean addShip(Ship ship) {
        boolean result = true;
        for (Ship s : ships) {
            if (s.getNumberId() == ship.getNumberId()) {
                result = false;
            }
        }
        if (result) {
            ships.add(ship);
        }

        return result;
    }

    public String getName() {
        return this.name;
    }


    public ArrayList<String> EnemiesInAir() {
        return board.getEnemiesInAir(this.IDENTIFICATION);
    }

    public List<Aircraft> getAircrafts() {
        return aircrafts;
    }

    public boolean itsAGoodAttack(int latitude, int longitude) {

        if (board.isThereAnyAlly(this.IDENTIFICATION,latitude, longitude)) {
            return false;
        }
        if (board.isThereAnyEnemy(this.IDENTIFICATION,latitude, longitude)){
            return true;
        }
        return false;
    }

    public List<AircraftCarrier> getCarriers() {
        return carriers;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public boolean moveOn(int deltaLatitude, int deltaLongitude) {
        boolean samePostion = false;
        for (Ship s: ships) {
            if (!(s.getLocation().getLatitude()+deltaLatitude <= board.MAX_LATITUDE && s.getLocation().getLatitude()+deltaLatitude >= board.MIN_LATITUDE)) {
                return false;
            }
            if ((!(s.getLocation().getLongitude()+deltaLongitude <= board.MAX_LONGITUDE && s.getLocation().getLongitude()+deltaLongitude >= board.MIN_LONGITUDE))) {
                return false;
            }
            for (Position e : board.getEnemyShips(this.IDENTIFICATION)) {

                  samePostion = s.getLocation().willBeInTheSamePosition(s.getLocation(),deltaLatitude,deltaLongitude,e);
                    if (samePostion) {
                        return false;
                    }
            }
            for (Position c : board.getEnemyAircraftCarriers(this.IDENTIFICATION)) {

                 samePostion = s.getLocation().willBeInTheSamePosition(s.getLocation(),deltaLatitude,deltaLongitude,c);
                if (samePostion) {
                    return false;
                }
            }
        }
        for (Ship s: ships) {
            s.getLocation().setLongitude(s.getLocation().getLongitude() + deltaLongitude);
            s.getLocation().setLatitude(s.getLocation().getLatitude() + deltaLongitude);
        }
        return true;
    }

    public int numberOfMachines () {
        return aircrafts.size() + carriers.size() +ships.size();
    }

    public boolean problemInAir() {
        ArrayList<String> enemies = new ArrayList<>();
        ArrayList<String> allies = new ArrayList<>();
        enemies = board.getEnemiesInAir(this.IDENTIFICATION);
        allies = board.getAlliesInAir(this.IDENTIFICATION);
        for (String a: allies) {
            for(String e: enemies){
                if (a ==e) {
                    return true;
                }
        }
        }
        return false;
    }

    //------------------//

    public void addMarine(Marine marine) {
        marines.add(marine);
    }

    public boolean enoughMarines() {
        boolean result = false;
        if ((this.marines.size()==0)) {
            return false;
        }
        int counter = aircrafts.size()*2 + carriers.size()*5 +ships.size()*4;
        if (marines.size()>= counter) {
            result = true;
        }
       return result;
    }
}