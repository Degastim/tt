package tt.hashtranslator.handler;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import tt.hashtranslator.exception.InvalidCredentialException;
import tt.hashtranslator.exception.InvalidFieldValueException;
import tt.hashtranslator.exception.ResourceNotFoundedException;

@ControllerAdvice
public class RuntimeExceptionHandler {
    @ExceptionHandler(ResourceNotFoundedException.class)
    public final ResponseEntity<ResponseMessage> handleResourceNotFoundedException(ResourceNotFoundedException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        String message = e.getLocalizedMessage();
        ResponseMessage responseMessage = new ResponseMessage(message);
        return ResponseEntity.status(httpStatus).body(responseMessage);
    }
    @ExceptionHandler(InvalidCredentialException.class)
    public final ResponseEntity<ResponseMessage> handleResourceNotFoundedException(InvalidCredentialException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        String message = e.getLocalizedMessage();
        ResponseMessage responseMessage = new ResponseMessage(message);
        return ResponseEntity.status(httpStatus).body(responseMessage);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<ResponseMessage> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        String message = e.getCause().getLocalizedMessage();
        ResponseMessage responseMessage = new ResponseMessage("Value isn't number " + message.toLowerCase());
        return ResponseEntity.status(httpStatus).body(responseMessage);
    }

    @ExceptionHandler(InvalidDefinitionException.class)
    public final ResponseEntity<ResponseMessage> handleInvalidDefinitionException(InvalidDefinitionException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String message = "Invalid request body structure";
        ResponseMessage responseMessage = new ResponseMessage(message);
        return ResponseEntity.status(httpStatus).body(responseMessage);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<ResponseMessage> handleInvalidDefinitionException(HttpMessageNotReadableException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String message = "Invalid request body structure";
        ResponseMessage responseMessage = new ResponseMessage(message);
        return ResponseEntity.status(httpStatus).body(responseMessage);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public final ResponseEntity<ResponseMessage> handleInvalidDefinitionException(HttpMediaTypeNotSupportedException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String message = "Unsupported Media Type";
        ResponseMessage responseMessage = new ResponseMessage(message);
        return ResponseEntity.status(httpStatus).body(responseMessage);
    }
    @ExceptionHandler(InvalidFieldValueException.class)
    public final ResponseEntity<ResponseMessage> handleInvalidDefinitionException(InvalidFieldValueException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String message =  e.getLocalizedMessage();
        ResponseMessage responseMessage = new ResponseMessage(message);
        return ResponseEntity.status(httpStatus).body(responseMessage);
    }
}