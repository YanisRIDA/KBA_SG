package kba.exception;

public class InvalidAmountException extends KBAException {

    private static final String ERROR_MESSAGE = "Invalid amount: ";
    public InvalidAmountException(long amount, String details) {
        super(ERROR_MESSAGE+amount, details);
    }

}
