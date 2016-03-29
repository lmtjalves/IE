package calculatecoordinatesdistanceservice;

public class ServiceParameters {

    private final String ORIGIN_PARAM = "origins";
    private final String DESTINATION_PARAM = "destinations";
    private final String KEY_PARAM = "key";
    
    private String origin_latitude;
    private String origin_longitude;
    private String destination_latitude;
    private String destination_longitude;
    
    public ServiceParameters(String origin_latitude, String origin_longitude,
                             String destination_latitude, String destination_longitude) {
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
