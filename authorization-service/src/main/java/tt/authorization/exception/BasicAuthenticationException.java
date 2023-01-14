package tt.authorization.exception;

public class BasicAuthenticationException extends RuntimeException {
    public BasicAuthenticationException(String message) {
        super(message);
    }
}