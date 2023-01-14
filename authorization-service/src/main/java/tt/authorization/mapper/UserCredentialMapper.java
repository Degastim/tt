package tt.authorization.mapper;

import org.springframework.stereotype.Component;
import tt.authorization.dto.UserCredential;
import tt.authorization.entity.User;

@Component
public class UserCredentialMapper implements DTOMapper<User, UserCredential> {

    @Override
    public User toEntity(UserCredential dto) {
        return new User(dto.getId(), dto.getEmail(), dto.getPassword());
    }

    @Override
    public UserCredential toDTO(User entity) {
        return new UserCredential(entity.getId(), entity.getEmail(), entity.getPassword());
    }
}
