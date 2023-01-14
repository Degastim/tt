package tt.hashtranslator.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class AccessRightValidator {
    private final String authorizationHeader = "Authorization";
    @Value("${authorization.server.url}")
    private String authorizationServerUrl;

    public boolean check(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader(authorizationHeader);
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("token", token);
        ResponseEntity<Boolean> response = restTemplate.getForEntity(authorizationServerUrl, Boolean.class, uriVariables);
        return Boolean.TRUE.equals(response.getBody());
    }
}
