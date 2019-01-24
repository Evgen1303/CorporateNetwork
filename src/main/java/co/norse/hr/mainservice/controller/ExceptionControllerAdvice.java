package co.norse.hr.mainservice.controller;

import co.norse.hr.mainservice.exception.EmployeeProjectNotFoundException;
import co.norse.hr.mainservice.exception.ProjectNotFoundException;
import co.norse.hr.mainservice.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ProjectNotFoundException.class)
    protected ResponseEntity<AwesomeException> handleProjectNotFoundException() {
        return new ResponseEntity<>(new AwesomeException("No such Project"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeProjectNotFoundException.class)
    protected ResponseEntity<AwesomeException> handleEmployeeProjectNotFoundException() {
        return new ResponseEntity<>(new AwesomeException("No such EmployeeProject"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<AwesomeException> handleResourceNotFoundException() {
        return new ResponseEntity<>(new AwesomeException("No such resource"), HttpStatus.NOT_FOUND);
    }

    private static class AwesomeException {
        private String message;

        @Autowired
        public AwesomeException(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }


    }

}
