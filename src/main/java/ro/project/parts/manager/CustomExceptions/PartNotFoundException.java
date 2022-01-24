package ro.project.parts.manager.CustomExceptions;

public class PartNotFoundException extends RuntimeException {

    public PartNotFoundException(String message) {
        super(message);
    }

    public PartNotFoundException() {
        super("This item doesn't exist");
    }

    public PartNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PartNotFoundException(Throwable cause) {
        super(cause);
    }
}
