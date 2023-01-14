package tt.authorization.security.decoder;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class Base64Decoder implements TokenDecoder {
    private static final String SPIT_REGEX = ":";

    @Override
    public String decodeEmail(String token) {
        return new String(Base64.getDecoder().decode(token)).split(SPIT_REGEX)[0];
    }

    @Override
    public String decodePassword(String token) {
        return new String(Base64.getDecoder().decode(token)).split(SPIT_REGEX)[1];
    }
}
