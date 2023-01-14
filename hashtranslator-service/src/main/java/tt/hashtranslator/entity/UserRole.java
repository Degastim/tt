package tt.hashtranslator.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "name"})
public class UserRole {
    private long id;
    private String name;
    private Set<User> userSet;
    private Set<Permission> permissions;
}
