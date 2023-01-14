package tt.authorization.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tt.authorization.security.BasicTokenProvider;
import tt.authorization.service.AuthService;

import static org.junit.jupiter.api.Assertions.assertFalse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthServiceImplTest {
    @Mock
    private BasicTokenProvider basicTokenProvider;
    private AuthService authService;

    @BeforeAll
    public void setup() {
        MockitoAnnotations.openMocks(this);
        authService = new AuthServiceImpl(basicTokenProvider);
    }

    @Test
    void checkToken_nullBasicToken_returnFalse() {
        //Given
        String token = null;

        //When
        boolean actual = authService.checkToken(token);

        //Then
        assertFalse(actual);
    }

    @Test
    void checkToken_notContainBasic_returnFalse() {
        //Given
        String token = "emhlbnlhQGdtYWlsLmNvbTpRd2VydHkjMTIz";

        //When
        boolean actual = authService.checkToken(token);

        //Then
        assertFalse(actual);
    }
}