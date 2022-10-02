package kba.exception;

public class BankAccountNotFoundException extends KBAException {

    private static final String ERROR_MESSAGE = "Invalid bank account: ";
    private static final String DETAILS = "Bank account not found";

    public BankAccountNotFoundException(int id) {
        super(ERROR_MESSAGE+id, DETAILS);
    }

}
