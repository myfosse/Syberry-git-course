package hotels.exceptions;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

public class ApiException {

  private final HashMap<String, String> errors;

  public ApiException(HashMap<String, String> errors, HttpStatus httpStatus) {
    this.errors = errors;
  }

  public HashMap<String, String> getErrors() {
    return errors;
  }
}
