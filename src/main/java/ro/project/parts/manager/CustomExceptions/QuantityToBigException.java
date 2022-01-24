package ro.project.parts.manager.CustomExceptions;

public class QuantityToBigException extends RuntimeException {
    public QuantityToBigException(final String message) {
        super(message);
    }

    public QuantityToBigException() {
        super("You don't have enough parts on stock");
    }
}
