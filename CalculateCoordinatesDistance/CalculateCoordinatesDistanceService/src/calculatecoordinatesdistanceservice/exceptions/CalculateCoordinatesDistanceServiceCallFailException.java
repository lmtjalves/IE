package calculatecoordinatesdistanceservice.exceptions;

public class CalculateCoordinatesDistanceServiceCallFailException extends Exception {
    
    public CalculateCoordinatesDistanceServiceCallFailException(Exception e) {
        super(e);
    }
    
    public CalculateCoordinatesDistanceServiceCallFailException(String message) {
        super(message);
    }
}
