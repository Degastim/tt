package tt.authorization.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tt.authorization.service.AuthService;
@Slf4j
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;

    @GetMapping
    public ResponseEntity<Boolean> checkToken(@RequestParam String token) {
        log.info("Checking token {}", token);
        boolean result = authService.checkToken(token);
        return ResponseEntity.ok(result);
    }
}
