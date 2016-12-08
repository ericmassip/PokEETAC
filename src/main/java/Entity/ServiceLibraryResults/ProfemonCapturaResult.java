package Entity.ServiceLibraryResults;

/**
 * Created by ericmassip on 7/12/16.
 */
public class ProfemonCapturaResult {
    public String name;
    public double latitude;
    public double longitude;

    public ProfemonCapturaResult fillInTheFields(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        return this;
    }
}
