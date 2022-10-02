package kba.exception;

public class EmptyParameterException extends KBAException {

    private static final String ERROR_MESSAGE = "Empty parameter: ";
    private static final String DETAILS = "Parameter cannot be empty";

    public EmptyParameterException(String parameter) {
        super(ERROR_MESSAGE+parameter, DETAILS);
    }
}
