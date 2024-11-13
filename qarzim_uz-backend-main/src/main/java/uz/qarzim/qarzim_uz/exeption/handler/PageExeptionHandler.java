package uz.qarzim.qarzim_uz.exeption.handler;

import uz.qarzim.qarzim_uz.exeption.PageSizeException;
import uz.qarzim.qarzim_uz.utill.AppConstants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class PageExeptionHandler {

    @ExceptionHandler(value = PageSizeException.class)
    public ResponseEntity<?> getExeption(HttpServletRequest req, Exception e) {
        return ResponseEntity.badRequest().body("Page is start 1 and size mustn't be more than " + AppConstants.MAX_PAGE_SIZE + ". Please check it.");
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpEntity<?> unknownException(MissingServletRequestParameterException ex, WebRequest req) {
        String paramName = ex.getParameterName();
        String message = ex.getMessage();
        Map<String, String> errors = new HashMap<>();
        errors.put(paramName, message);

        return new ResponseEntity<>(new ErrorDetails("Validator errors!", errors), HttpStatus.BAD_REQUEST);
    }

    private static class ErrorDetails {
        private final String message;
        private final Map<String, String> errors;
        private final int dataStatus;

        public ErrorDetails(String message, Map<String, String> errors) {
            this.message = message;
            this.errors = errors;
            this.dataStatus = 0;
        }


        public int getDataStatus() {
            return dataStatus;
        }

        public String getMessage() {
            return message;
        }

        public Map<String, String> getErrors() {
            return errors;
        }
    }
}
