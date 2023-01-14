package tt.authorization.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import tt.authorization.exception.BasicAuthenticationException;
import tt.authorization.exception.ResourceNotFoundedException;
import tt.authorization.handler.ResponseMessage;
import tt.authorization.security.BasicTokenProvider;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@AllArgsConstructor
public class BasicAuthenticationFilter extends GenericFilterBean {
    private final BasicTokenProvider basicTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("Checking token in filter");
        String basicToken = basicTokenProvider.resolveToken((HttpServletRequest) request);
        try {
            if (basicToken != null) {
                if (!basicTokenProvider.validateToken(basicToken)) {
                    log.warn("Exception because of value basicToken= {}"+basicToken);
                    throw new BasicAuthenticationException("Invalid credentials");
                }
                Authentication authentication = basicTokenProvider.getAuthentication(basicToken);
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            chain.doFilter(request, response);
        } catch (ResourceNotFoundedException | BasicAuthenticationException e) {
            sendResponse((HttpServletResponse) response, e.getMessage());
        }
    }

    private void sendResponse(HttpServletResponse servletResponse, String message) throws IOException {
        SecurityContextHolder.clearContext();
        servletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        servletResponse.setContentType("application/json");
        ResponseMessage entity = new ResponseMessage(message);
        ObjectMapper objectMapper = new ObjectMapper();
        servletResponse.getOutputStream().print(objectMapper.writeValueAsString(entity));
    }
}