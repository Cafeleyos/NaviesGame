/**
 * Class that give the machines a perception of position through the board.
 * @author Camilo Muñoz
 */
public class Position {
    private int latitude;
    private int longitude;
    private Board board = new Board(null);

    public int getLatitude() {
        return latitude;

    }

    public void setLatitude(int latitude) {
        if (board.MIN_LATITUDE<= latitude && latitude <= board.MAX_LATITUDE) {
            this.latitude = latitude;
        }

    }
    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        if (board.MIN_LONGITUDE<= longitude && longitude <= board.MAX_LONGITUDE)
        this.longitude = longitude;
    }

    public boolean willBeInTheSamePosition(Position current, int latitude, int longitude, Position comparison){
        if (current.getLatitude() +latitude == comparison.getLatitude() &&
            current.getLongitude() + longitude== comparison.getLongitude()) {
            return true;
        }
        return false;
        }
}
