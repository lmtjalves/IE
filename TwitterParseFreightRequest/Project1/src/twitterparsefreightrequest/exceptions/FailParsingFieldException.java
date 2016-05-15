package twitterparsefreightrequest.exceptions;

public class FailParsingFieldException extends Exception {
    private String message;
    public FailParsingFieldException(String message) {
        super();
        this.message = message;
    }
    
    public String getMessage(){
        return this.message;
    }
}
