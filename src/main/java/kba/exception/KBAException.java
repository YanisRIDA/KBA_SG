package kba.exception;

public class KBAException extends Exception {

    private String details;
    public KBAException(String errorMessage, String details) {
        super(errorMessage);
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

}
