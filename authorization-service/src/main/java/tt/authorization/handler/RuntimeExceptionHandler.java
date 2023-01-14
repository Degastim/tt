package tt.authorization.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import tt.authorization.exception.InvalidFieldValueException;
import tt.authorization.exception.ResourceAlreadyExistException;
import tt.authorization.exception.ResourceNotFoundedException;

@ControllerAdvice
public class RuntimeExceptionHandler {
    @ExceptionHandler(ResourceNotFoundedException.class)
    public final ResponseEntity<ResponseMessage> handleResourceNotFoundedException(ResourceNotFoundedException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        String message = e.getLocalizedMessage();
        ResponseMessage responseMessage = new ResponseMessage(message);
        return ResponseEntity.status(httpStatus).body(responseMessage);
    }

    @ExceptionHandler(InvalidFieldValueException.class)
    public final ResponseEntity<ResponseMessage> handleInvalidFieldValueException(InvalidFieldValueException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String message = e.getLocalizedMessage();
        ResponseMessage responseMessage = new ResponseMessage(message);
        return ResponseEntity.status(httpStatus).body(responseMessage);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public final ResponseEntity<ResponseMessage> handleResourceAlreadyExistException(ResourceAlreadyExistException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String message = e.getLocalizedMessage();
        ResponseMessage responseMessage = new ResponseMessage(message);
        return ResponseEntity.status(httpStatus).body(responseMessage);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<ResponseMessage> handleMethodArgumentTypeMismatchException() {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String message = "Invalid number data in URL";
        ResponseMessage responseMessage = new ResponseMessage(message);
        return ResponseEntity.status(httpStatus).body(responseMessage);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<ResponseMessage> handleHttpMessageNotReadableException() {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String message = "Invalid data entered in the request body";
        ResponseMessage responseMessage = new ResponseMessage(message);
        return ResponseEntity.status(httpStatus).body(responseMessage);
    }

    @ExceptionHandler(BindException.class)
    public final ResponseEntity<ResponseMessage> handleBindException() {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String message = "Invalid data entered in the URL";
        ResponseMessage responseMessage = new ResponseMessage(message);
        return ResponseEntity.status(httpStatus).body(responseMessage);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<ResponseMessage> handleBadCredentialsException() {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String message = "Invalid user credentials";
        ResponseMessage responseMessage = new ResponseMessage(message);
        return ResponseEntity.status(httpStatus).body(responseMessage);
    }

    @ExceptionHandler(LockedException.class)
    public final ResponseEntity<ResponseMessage> handleLockedException() {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String message = "User account locked";
        ResponseMessage responseMessage = new ResponseMessage(message);
        return ResponseEntity.status(httpStatus).body(responseMessage);
    }
}
