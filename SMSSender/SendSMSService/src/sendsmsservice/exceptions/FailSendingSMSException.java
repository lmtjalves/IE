package sendsmsservice.exceptions;

/**
 * Use when fails sending an SMS.
 */
public class FailSendingSMSException extends Exception {
    public FailSendingSMSException(Exception e) {
        super(e);
    }
}
