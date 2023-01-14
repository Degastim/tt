package tt.authorization.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import tt.authorization.dto.UserCredential;
import tt.authorization.dto.UserDTO;

public interface UserService extends UserDetailsService {
    UserDTO create(UserCredential userCredential);

    void delete(long id);

    UserDTO findByEmail(String email);

    boolean validateCredential(String email, String password);
}
