package calculatecoordinatesdistanceservice;

public class ServiceParameters {

    private final String ORIGIN_PARAM = "origins";
    private final String DESTINATION_PARAM = "destinations";
    private final String KEY_PARAM = "key";
    
    private double origin_latitude;
    private double origin_longitude;
    private double destination_latitude;
    private double destination_longitude;
    
    public ServiceParameters(double origin_latitude, double origin_longitude,
                             double destination_latitude, double destination_longitude) {
        super();
        
        this.origin_latitude = origin_latitude;
        this.origin_longitude = origin_longitude;
        this.destination_latitude = destination_latitude;
        this.destination_longitude = destination_longitude;
        
    }
    
    @Override
    public String toString() {
        return ORIGIN_PARAM + "=" + this.origin_latitude + "," + this.origin_longitude + "&" +
            DESTINATION_PARAM + "=" + this.destination_latitude + "," + this.destination_longitude + "&" +
            KEY_PARAM + "=";
    }
}
