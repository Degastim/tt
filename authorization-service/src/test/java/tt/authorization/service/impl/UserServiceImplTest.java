package tt.authorization.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import tt.authorization.converter.UserDetailsConverter;
import tt.authorization.dao.UserDao;
import tt.authorization.dao.UserRoleDao;
import tt.authorization.dto.UserCredential;
import tt.authorization.dto.UserDTO;
import tt.authorization.entity.User;
import tt.authorization.entity.UserRole;
import tt.authorization.exception.ResourceNotFoundedException;
import tt.authorization.mapper.UserCredentialMapper;
import tt.authorization.mapper.UserDTOMapper;
import tt.authorization.service.UserService;
import tt.authorization.validator.UserCredentialValidator;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceImplTest {
    @Mock
    private PasswordEncoder encoder;
    @Mock
    private UserCredentialValidator userCredentialValidator;
    @Mock
    private UserCredentialMapper userCredentialMapper;
    @Mock
    private UserDao userDao;
    @Mock
    private UserRoleDao userRoleDao;
    @Mock
    private UserDTOMapper userDTOMapper;
    @Mock
    private UserDetailsConverter userDetailsConverter;
    private UserService userService;

    @BeforeAll
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(encoder, userCredentialValidator, userCredentialMapper,
                userDao, userRoleDao, userDTOMapper, userDetailsConverter);
    }

    @Test
    void create() {
        //Given
        UserCredential userCredential = new UserCredential(1, "john@gmail.com", "q151g34h245#4n5");
        Mockito.doNothing().when(userCredentialValidator).isUserValid(userCredential);
        User user = new User("john@gmail.com", "q151g34h245#4n5");
        Mockito.when(userCredentialMapper.toEntity(userCredential))
                .thenReturn(user);
        UserRole userRole = new UserRole("USER");
        Optional<UserRole> userRoleOptional = Optional.of(userRole);
        Mockito.when(userRoleDao.findByName("USER"))
                .thenReturn(userRoleOptional);
        user.setUserRole(userRole);
        user.setActive(true);
        user.setPassword("Qwerty#123");
        Mockito.when(encoder.encode(Mockito.eq("q151g34h245"))).thenReturn("Qwerty#123");
        Mockito.when(userDao.save(user)).thenReturn(user);
        Mockito.when(userDTOMapper.toDTO(user)).thenReturn(new UserDTO(1, "john@gmail.com", "q151g34h245#4n5"));
        UserDTO expected = new UserDTO(1, "john@gmail.com", "q151g34h245#4n5");

        //When
        UserDTO actual = userService.create(userCredential);

        //Then
        assertEquals(expected, actual);
    }


    @Test
    void findByEmail_trueValue_equalValue() {
        //Given
        String email = "john@gmail.com";
        User user = new User(1, email, "q151g34h245#4n5");
        Optional<User> optionalUser = Optional.of(user);
        Mockito.when(userDao.findByEmail(email)).thenReturn(optionalUser);
        UserDTO userDTO = new UserDTO(1, "john@gmail.com", "q151g34h245#4n5");
        Mockito.when(userDTOMapper.toDTO(user)).thenReturn(userDTO);
        UserDTO expected = new UserDTO(1, "john@gmail.com", "q151g34h245#4n5");
        //When
        UserDTO actual = userService.findByEmail(email);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    void findByEmail_noEmail_throwException() {
        //Given
        String email = "john@gmail.com";
        User user = new User(1, email, "q151g34h245#4n5");
        Mockito.when(userDao.findByEmail(email)).thenReturn(Optional.empty());
        UserDTO userDTO = new UserDTO(1, "john@gmail.com", "q151g34h245#4n5");
        Mockito.when(userDTOMapper.toDTO(user)).thenReturn(userDTO);

        //When-Then
        assertThrows(ResourceNotFoundedException.class, () -> userService.findByEmail(email));
    }

    @Test
    void validateCredential_noEmail_returnFalse() {
        //Given
        String email = "john@gmail.com";
        String password = "Qwerty#123";
        Mockito.when(userDao.findByEmail(email)).thenReturn(Optional.empty());

        //When
        boolean actual = userService.validateCredential(email, password);

        //Then
        assertFalse(actual);
    }

    @Test
    void validateCredential_passwordsNotMatches_returnFalse() {
        //Given
        String email = "john@gmail.com";
        String password = "Qwerty#123";
        User user = new User(1, email, "q151g34h245#4n5");
        Mockito.when(userDao.findByEmail(email)).thenReturn(Optional.of(user));
        Mockito.when(encoder.matches("Qwerty#123", "q151g34h245#4n5")).thenReturn(false);

        //When
        boolean actual = userService.validateCredential(email, password);

        //Then
        assertFalse(actual);
    }

    @Test
    void validateCredential_passwordsMatches_returnTrue() {
        //Given
        String email = "john@gmail.com";
        String password = "Qwerty#123";
        User user = new User(1, email, "q151g34h245#4n5");
        Mockito.when(userDao.findByEmail(email)).thenReturn(Optional.of(user));
        Mockito.when(encoder.matches("Qwerty#123", "q151g34h245#4n5")).thenReturn(true);

        //When
        boolean actual = userService.validateCredential(email, password);

        //Then
        assertTrue(actual);
    }

    @Test
    void loadUserByUsername_haventUsers_throwException() {
        //Given
        String email = "john@gmail.com";
        Mockito.when(userDao.findByEmail(email)).thenReturn(Optional.empty());

        //When-Then
        assertThrows(ResourceNotFoundedException.class, () -> userService.loadUserByUsername(email));
    }

    @Test
    void loadUserByUsername_haveUsers_throwException() {
        //Given
        String email = "john@gmail.com";
        User user = new User(1, email, "q151g34h245#4n5");
        Mockito.when(userDao.findByEmail(email)).thenReturn(Optional.of(user));
        UserDetails expected = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.isActive(),
                user.isActive(),
                user.isActive(),
                user.isActive(),
                new HashSet<>());
        Mockito.when(userDetailsConverter.convert(user)).thenReturn(expected);

        //When
        UserDetails actual = userService.loadUserByUsername(email);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    void delete_haventId_thowException() {
        //Given
        long id = 1;
        Mockito.when(userDao.findById(id)).thenReturn(Optional.empty());

        //When-Then
        assertThrows(ResourceNotFoundedException.class, () -> userService.delete(id));
    }

    @Test
    void delete_haveId_throwException() {
        //Given
        long id = 1;
        User user = new User(id, "john@gmail.com", "q151g34h245#4n5");
        Mockito.when(userDao.findById(id)).thenReturn(Optional.of(user));

        //When-Then
        assertDoesNotThrow(() -> userService.delete(id));
    }
}