package kba.exception;

public class NegativeBalanceException extends InvalidAmountException {

    private static final String DETAILS = "New balance after operation cannot be less than 0";

    public NegativeBalanceException(long amount) {
        super(amount, DETAILS);
    }
}
