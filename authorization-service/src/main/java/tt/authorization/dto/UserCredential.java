package tt.authorization.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCredential {
    private long id;
    private String email;
    private String password;
}
