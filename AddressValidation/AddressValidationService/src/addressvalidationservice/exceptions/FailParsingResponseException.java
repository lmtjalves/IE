package addressvalidationservice.exceptions;

public class FailParsingResponseException extends Exception {
    public FailParsingResponseException(Exception e) {
        super(e);
    }
}
