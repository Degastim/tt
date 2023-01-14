package tt.authorization.exception;

public class ResourceNotFoundedException extends RuntimeException {
    public ResourceNotFoundedException(String message) {
        super(message);
    }
}

