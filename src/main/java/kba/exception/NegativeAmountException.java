package kba.exception;

public class NegativeAmountException extends InvalidAmountException {

    private static final String DETAILS = "Amount value cannot be less than 0";

    public NegativeAmountException(long amount) {
        super(amount, DETAILS);
    }
}
