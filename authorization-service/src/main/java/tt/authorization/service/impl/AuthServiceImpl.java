package tt.authorization.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tt.authorization.security.BasicTokenProvider;
import tt.authorization.service.AuthService;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final int BASIC_INDENT = 6;
    private BasicTokenProvider basicTokenProvider;

    @Override
    public boolean checkToken(String basicToken) {
        if(basicToken==null|| !basicToken.contains("Basic ")){
            return false;
        }
        String token = basicToken.substring(BASIC_INDENT);
        return basicTokenProvider.validateToken(token);
    }
}
