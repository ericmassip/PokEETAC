package Entity.ServiceLibraryResults;

/**
 * Created by ericmassip on 7/12/16.
 */
public class ProfemonLocationResult {
    public int profemonId;
    public String name;
    public double latitude;
    public double longitude;
    public int floor;

    public ProfemonLocationResult fillInTheFields(int profemonId, String name, double latitude, double longitude, int floor) {
        this.profemonId = profemonId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.floor = floor;
        return this;
    }
}
