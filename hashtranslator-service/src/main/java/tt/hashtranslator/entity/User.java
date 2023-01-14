package tt.hashtranslator.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "userRole")
public class User {
    private long id;
    private String email;
    private String password;
    private boolean isActive;
    private UserRole userRole;
}
