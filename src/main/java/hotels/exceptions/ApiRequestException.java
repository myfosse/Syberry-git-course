package hotels.exceptions;

import java.util.HashMap;

public class ApiRequestException extends RuntimeException {

  private HashMap<String, String> errors;

  public HashMap<String, String> getErrors() {
    return errors;
  }

  public ApiRequestException(HashMap<String, String> errors) {
    super(errors.toString());
    this.errors = errors;
  }

  public ApiRequestException(String message, Throwable cause) {
    super(message, cause);
  }

  @Override
  public String getMessage() {
    return errors.toString();
  }
}
