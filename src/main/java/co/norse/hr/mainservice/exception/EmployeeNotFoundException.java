package co.norse.hr.mainservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Employee")
public class EmployeeNotFoundException extends EntityNotFoundException {
}
