package tt.authorization.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tt.authorization.dto.UserCredential;
import tt.authorization.dto.UserDTO;
import tt.authorization.service.UserService;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasAuthority('users:create')")
    @PostMapping
    public UserDTO create(@RequestBody UserCredential userCredential) {
        log.info("Creating user {}", userCredential);
        return userService.create(userCredential);
    }

    @PreAuthorize("hasAuthority('users:delete')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        log.info("Deleting user with id{}", id);
        userService.delete(id);
    }
}
