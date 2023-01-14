package tt.authorization.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

import java.util.Optional;


@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder encoder;
    private final UserCredentialValidator userCredentialValidator;
    private final UserCredentialMapper userCredentialMapper;
    private final UserDao userDao;
    private final UserRoleDao userRoleDao;
    private final UserDTOMapper userDTOMapper;
    private final UserDetailsConverter userDetailsConverter;
    private final String DEFAULT_USER_ROLE = "USER";

    @Override
    @Transactional
    public UserDTO create(UserCredential userCredential) {
        userCredentialValidator.isUserValid(userCredential);
        User user = userCredentialMapper.toEntity(userCredential);
        UserRole userRole = userRoleDao.findByName(DEFAULT_USER_ROLE)
                .orElseThrow(() -> new ResourceNotFoundedException("No user role"));
        user.setUserRole(userRole);
        user.setActive(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user = userDao.save(user);
        return userDTOMapper.toDTO(user);
    }

    @Override
    @Transactional
    public void delete(long id) {
        User user = userDao.findById(id).orElseThrow(() ->
                new ResourceNotFoundedException("Requested resource not found (id)=" + id));
        userDao.delete(user);
    }

    @Override
    public UserDTO findByEmail(String email) {
        User user = userDao.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundedException("No such user was found."));
        return userDTOMapper.toDTO(user);
    }

    @Override
    public boolean validateCredential(String email, String rawPassword) {
        Optional<User> userOptional = userDao.findByEmail(email);
        if (!userOptional.isPresent()) {
            return false;
        }
        String password = userOptional.get().getPassword();
        return encoder.matches(rawPassword, password);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundedException("User with this email not found"));
        return userDetailsConverter.convert(user);
    }
}
