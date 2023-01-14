package tt.authorization.converter;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import tt.authorization.entity.User;

@Component
public class UserDetailsConverter {
    private final AuthorityConverter authorityConverter;

    public UserDetailsConverter(AuthorityConverter authorityConverter) {
        this.authorityConverter = authorityConverter;
    }

    public UserDetails convert(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.isActive(),
                user.isActive(),
                user.isActive(),
                user.isActive(),
                authorityConverter.convert(user.getUserRole()));
    }
}
