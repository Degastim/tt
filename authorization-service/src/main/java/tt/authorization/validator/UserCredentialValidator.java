package tt.authorization.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tt.authorization.dao.UserDao;
import tt.authorization.dto.UserCredential;
import tt.authorization.exception.InvalidFieldValueException;
import tt.authorization.exception.ResourceAlreadyExistException;

import java.util.regex.Pattern;

@Component
public class UserCredentialValidator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("[а-яА-Я\\w\\s\\p{Punct}]{6,256}");
    private final UserDao userDao;

    @Autowired
    public UserCredentialValidator(UserDao userDao) {
        this.userDao = userDao;
    }

    public void isUserValid(UserCredential user) {
        StringBuilder errorMessage = new StringBuilder();
        String email = user.getEmail();
        if (!isEmailValid(email)) {
            errorMessage.append("User email is not valid.");
        }
        if (!isPasswordValid(user.getPassword())) {
            errorMessage.append("Password is not valid.");
        }
        if (errorMessage.length() != 0) {
            throw new InvalidFieldValueException(errorMessage.toString());
        }
        if (userDao.findByEmail(email).isPresent()) {
            throw new ResourceAlreadyExistException("There is already a user with the same name.");
        }
    }

    private boolean isEmailValid(String name) {
        return (name != null && EMAIL_PATTERN.matcher(name).matches());
    }

    private boolean isPasswordValid(String password) {
        return (password != null && PASSWORD_PATTERN.matcher(password).matches());
    }
}