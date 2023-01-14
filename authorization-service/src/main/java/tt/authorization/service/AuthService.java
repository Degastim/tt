package tt.authorization.service;

import org.springframework.security.core.Authentication;

public interface AuthService {
    boolean checkToken(String token);
}
