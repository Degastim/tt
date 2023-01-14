package tt.authorization.converter;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import tt.authorization.entity.Permission;
import tt.authorization.entity.UserRole;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AuthorityConverter {
    public Set<SimpleGrantedAuthority> convert(UserRole userRole) {
        Set<Permission> permissions = userRole.getPermissions();
        return permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toSet());
    }
}

