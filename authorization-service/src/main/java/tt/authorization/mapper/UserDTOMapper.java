package tt.authorization.mapper;

import org.springframework.stereotype.Component;
import tt.authorization.dto.UserDTO;
import tt.authorization.entity.User;
import tt.authorization.entity.UserRole;

@Component
public class UserDTOMapper implements DTOMapper<User, UserDTO> {
    @Override
    public UserDTO toDTO(User user) {
        return new UserDTO(user.getId(), user.getEmail(), user.getUserRole().getName());
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        String userRoleName = userDTO.getUserRoleName();
        return new User(userDTO.getId(), userDTO.getEmail(), new UserRole(userRoleName));
    }
}
