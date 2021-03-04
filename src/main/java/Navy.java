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
        this.carriers = new ArrayList<>();
        this.aircrafts = new ArrayList<>();
        this.ships = new ArrayList<>();
        this.marines = new ArrayList<>();
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
     * Adds an Aircraft to the Navy and to the respective AircraftCarrier if the carrier has availability.
     *
     * @return if it was added or not
     *
     */
    public boolean addAircraft(Aircraft aircraft) {
        boolean result = true;
        if (aircraft.getCarrier().getAvailability() > 0) {


            for (Aircraft a : aircrafts) {
                if (a.getLicencePlate().equals(aircraft.getLicencePlate())) {
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
    /**
     * Adds a new ship to the navy
     *
     * @param ship to be added
     * @return if it was added or not taking into account its ID
     */
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

    /**
     * Calls for the enemy aircrafts that are flying
     *
     * @return the list of enemies in Air
     */
    public ArrayList<String> EnemiesInAir() {
        return board.getEnemiesInAir(this.IDENTIFICATION);
    }


    public List<Aircraft> getAircrafts() {
        return aircrafts;
    }

    /**
     * Defines if attacking in a certain position its a good idea taking into account the allies, enemies or just water that is there.
     * @param latitude for the attack
     * @param longitude for the attack
     * @return ¿its a good attack?
     */
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

    /**
     * If its possible(without crashing with an enemy or setting out of bounds), it moves all the ships and AircraftCarriers the amount defined.
     * @param deltaLatitude amount through latitude.
     * @param deltaLongitude amount through longitude.
     * @return if it was possible or not.
     */
    public boolean moveOn(int deltaLatitude, int deltaLongitude) {
        boolean samePostion;
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

    /**
     * Gets the total amount of machines that a navy has.
     *
     * @return the number of machines.
     */
    public int numberOfMachines () {
        return aircrafts.size() + carriers.size() +ships.size();
    }

    /**
     * Defines if an Enemy aircraft can be confused with an ally one comparing its licence plate.
     *
     * @return if there is a problem or not.
     */
    public boolean problemInAir() {
        ArrayList<String> enemies;
        ArrayList<String> allies ;
        enemies = board.getEnemiesInAir(this.IDENTIFICATION);
        allies = board.getAlliesInAir(this.IDENTIFICATION);
        for (String a: allies) {
            for(String e: enemies){
                if (a.equals(e)) {
                    return true;
                }
        }
        }
        return false;
    }

    public void addMarine(Marine marine) {
        marines.add(marine);
    }

    /**
     * Specifies if there are enough marines in the navy for the machines.
     * Each machine has a number of marines required:
     * Aircrafts: 2
     * AircraftCarriers: 5
     * Ships: 4
     *
     */
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

    /**
     * Determines which machines will be destroyed in case a bomb is launched to an specific coordinate.
     * @param latitude for the bomb
     * @param longitude for the bomb
     *
     * In this case the bomb has a blast radius of 3*3.
     *
     * If none machines will be destroyed it will return null.
     *
     * @return the machines that will be destroyed
     */
    public ArrayList<Object> willBeDestroyed(int latitude,int longitude) {
        ArrayList<Object> machines = new ArrayList<>();
        ArrayList<Object> temporal;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                temporal =(board.isThereAnyone(latitude+i,longitude+j));
                if (!(temporal.size()== 0)){
                    machines.addAll(temporal);
                }
            }
        }
        if (machines.size() == 0) {
            return null;
        }
        return machines;
    }

}