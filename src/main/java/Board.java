/**
 * @author Camilo Muñoz
 */
import java.util.ArrayList;


public class Board {
    private ArrayList<Navy> navies;
    public final int MIN_LATITUDE = -100;
    public final int MIN_LONGITUDE = -100;
    public final int MAX_LATITUDE = 100;
    public final int MAX_LONGITUDE = 100;

    public Board(ArrayList<Navy> navies) {
        this.navies = navies;
    }

    /**
     * @param name Counts the navies with an specific name
     * @return number of navies
     */

    public int countNavys(String name) {
        int count = 0;
        for (Navy navy : this.navies) {
            if (navy.getName().equals(name))
                count++;
        }
        return count;
    }

    public ArrayList<String> getEnemiesInAir(int id) {
        ArrayList<String> inAirAircrafts = new ArrayList<>();
        for (Navy n : navies) {
            if (!(n.IDENTIFICATION == id)) {
                for (Aircraft a : n.getAircrafts()) {
                    if (a.getInAir()) {
                        inAirAircrafts.add(a.getLicencePlate());
                    }
                }
            }
        }

        return inAirAircrafts;

    }

    public boolean isThereAnyAlly(int identification, int latitude, int longitude) {
        for (Navy n : navies) {
            if ((n.IDENTIFICATION == identification)) {
                for (Aircraft a : n.getAircrafts()) {
                    if (a.getLocation().getLatitude() == latitude && a.getLocation().getLongitude() == longitude) {
                        return true;
                    }
                }
                for (AircraftCarrier a : n.getCarriers()) {
                    if (a.getLocation().getLatitude() == latitude && a.getLocation().getLongitude() == longitude) {
                        return true;
                    }
                }
                for (Ship a : n.getShips()) {
                    if (a.getLocation().getLatitude() == latitude && a.getLocation().getLongitude() == longitude) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isThereAnyEnemy(int identification, int latitude, int longitude) {
        for (Navy n : navies) {
            if (!(n.IDENTIFICATION == identification)) {
                for (Aircraft a : n.getAircrafts()) {
                    if (a.getLocation().getLatitude() == latitude && a.getLocation().getLongitude() == longitude) {
                        return true;
                    }
                }
                for (AircraftCarrier a : n.getCarriers()) {
                    if (a.getLocation().getLatitude() == latitude && a.getLocation().getLongitude() == longitude) {
                        return true;
                    }
                }
                for (Ship a : n.getShips()) {
                    if (a.getLocation().getLatitude() == latitude && a.getLocation().getLongitude() == longitude) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public ArrayList<Position> getEnemyShips(int identification) {
        ArrayList<Position> Enemyships = new ArrayList<Position>();
        for (Navy n : navies) {
            if (!(n.IDENTIFICATION == identification)) {
                for (Ship a : n.getShips()) {
                    Enemyships.add(a.getLocation());
                }
            }
        }
        return Enemyships;
    }

    public ArrayList<Position> getEnemyAircraftCarriers(int identification) {
        ArrayList<Position> EnemyAircraftCarriers = new ArrayList<Position>();
        for (Navy n : navies) {
            if (!(n.IDENTIFICATION == identification)) {
                for (AircraftCarrier a : n.getCarriers()) {
                    EnemyAircraftCarriers.add(a.getLocation());
                }
            }
        }
        return EnemyAircraftCarriers;
    }


    public ArrayList<String> getAlliesInAir(int id) {
        ArrayList<String> inAirAircrafts = new ArrayList<>();
        for (Navy n : navies) {
            if ((n.IDENTIFICATION == id)) {
                for (Aircraft a : n.getAircrafts()) {
                    if (a.getInAir()) {
                        inAirAircrafts.add(a.getLicencePlate());
                    }
                }
            }
        }

        return inAirAircrafts;
    }

    //++++++++++++++++++++++

}