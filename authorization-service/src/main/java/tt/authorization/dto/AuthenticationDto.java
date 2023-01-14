package tt.authorization.dto;

import lombok.Data;
import org.springframework.security.core.Authentication;

@Data
public class AuthenticationDto {
    private Authentication authentication;
}
