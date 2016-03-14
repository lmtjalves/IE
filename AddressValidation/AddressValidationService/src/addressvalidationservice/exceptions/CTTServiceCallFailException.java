package addressvalidationservice.exceptions;

/**
 * Use this exception when a call to the CTT service fails for any reason.
 */
public class CTTServiceCallFailException extends Exception {
    public CTTServiceCallFailException(Exception e) {
        super(e);
    }
    
    public CTTServiceCallFailException(String message) {
        super(message);
    }
}
