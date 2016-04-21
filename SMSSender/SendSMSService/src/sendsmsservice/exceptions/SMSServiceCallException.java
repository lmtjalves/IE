package sendsmsservice.exceptions;

public class SMSServiceCallException extends Exception {
    public SMSServiceCallException(Exception e) {
        super(e);
    }
    
    public SMSServiceCallException(String message) {
        super(message);
    }
}
