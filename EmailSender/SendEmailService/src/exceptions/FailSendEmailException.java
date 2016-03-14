package exceptions;

/**
 * Use when fails sending and email.
 */
public class FailSendEmailException extends Exception {
    public FailSendEmailException(Exception e) {
        super(e);
    }
}
