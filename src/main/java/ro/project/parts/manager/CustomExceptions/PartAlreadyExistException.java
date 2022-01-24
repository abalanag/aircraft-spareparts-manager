package ro.project.parts.manager.CustomExceptions;

public class PartAlreadyExistException extends RuntimeException {

    public PartAlreadyExistException(String message) {
        super(message);
    }

    public PartAlreadyExistException() {
        super("There is already a part created with this name");
    }

    public PartAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public PartAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
