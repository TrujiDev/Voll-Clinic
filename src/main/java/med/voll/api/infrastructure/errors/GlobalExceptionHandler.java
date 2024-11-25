package med.voll.api.infrastructure.errors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity handleEntityNotFound() {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
    var errors = e.getFieldErrors().stream().map(ErrorValidationData::new).toList();
    return ResponseEntity.badRequest().body(errors);
  }

  private record ErrorValidationData(String field, String msg) {
    public ErrorValidationData(FieldError error) {
      this(error.getField(), error.getDefaultMessage());
    }
  }

}
