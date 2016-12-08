package Entity.ServiceLibraryResults;

/**
 * Created by ericmassip on 7/12/16.
 */
public class ProfemonCapturaResult {
    public String name;
    public double latitude;
    public double longitude;
    public int floor;

    public ProfemonCapturaResult fillInTheFields(String name, double latitude, double longitude, int floor) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.floor = floor;
        return this;
    }
}
