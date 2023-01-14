package tt.authorization.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import tt.authorization.security.decoder.TokenDecoder;
import tt.authorization.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Component
public class BasicTokenProvider {
    private final UserService userService;
    private final TokenDecoder tokenDecoder;
    private final int BASIC_INDENT = 6;
    @Value("${token.header:Authorization}")
    private String authorizationHeader;

    @Autowired
    public BasicTokenProvider(UserService userService, TokenDecoder tokenDecoder) {
        this.userService = userService;
        this.tokenDecoder = tokenDecoder;
    }

    public String resolveToken(HttpServletRequest request) {
        String basicToken = request.getHeader(authorizationHeader);
        return basicToken != null ? basicToken.substring(BASIC_INDENT) : null;
    }

    public boolean validateToken(String token) {
        String email = tokenDecoder.decodeEmail(token);
        String password = tokenDecoder.decodePassword(token);
        return userService.validateCredential(email, password);
    }

    public Authentication getAuthentication(String token) {
        String email = tokenDecoder.decodeEmail(token);
        UserDetails userDetails = userService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
