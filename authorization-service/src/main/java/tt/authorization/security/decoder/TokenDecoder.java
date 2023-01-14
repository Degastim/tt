package tt.authorization.security.decoder;

public interface TokenDecoder {
    String decodeEmail(String token);
    String decodePassword(String token);
}
