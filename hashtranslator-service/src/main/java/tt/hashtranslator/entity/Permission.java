package tt.hashtranslator.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = {"id", "name"})
public class Permission {
    private long id;
    private String name;
    private Set<UserRole> userRoles;

}
