package hotels.services.Impls;

import hotels.repositories.UserRepository;
import hotels.services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationServiceImpl implements ValidationService {

  @Autowired UserRepository userRepository;

  private static final String USERNAME_PATTERN = "^[A-z .,-_]{1,60}$";
  private static final String EMAIL_PATTERN = "^[A-z0-9+_.-]+@[A-Za-z0-9.-]+$";
  private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,64}$";

  private static final String NOT_VALID_USERNAME_MESSAGE = "Not valid username";
  private static final String USERNAME_ALREADY_EXIST_MESSAGE = "Username already exist";
  private static final String NOT_VALID_EMAIL_MESSAGE = "Not valid email";
  private static final String EMAIL_ALREADY_EXIST__MESSAGE = "Email already exist";
  private static final String PASSWORD_MATCH_USERNAME_MESSAGE = "Password must not match the name";
  private static final String NOT_VALID_PASSWORD_MESSAGE = "Not valid password";

  HashMap<String, String> errors = new HashMap<>();

  @Override
  public void checkUsername(String username) {
    if (isNotValidField(username, USERNAME_PATTERN)) {
      errors.put("username", NOT_VALID_USERNAME_MESSAGE);
      return;
    }

    if (isUsernameTaken(username)) {
      errors.put("username", USERNAME_ALREADY_EXIST_MESSAGE);
    }
  }

  @Override
  public void checkEmail(String email) {
    if (isNotValidField(email, EMAIL_PATTERN)) {
      errors.put("email", NOT_VALID_EMAIL_MESSAGE);
      return;
    }

    if (isEmailTaken(email)) {
      errors.put("email", EMAIL_ALREADY_EXIST__MESSAGE);
    }
  }

  @Override
  public void checkPassword(String password, String username) {
    if (password.matches(username)) {
      errors.put("password", PASSWORD_MATCH_USERNAME_MESSAGE);
      return;
    }

    if (isNotValidField(password, PASSWORD_PATTERN)) {
      errors.put("password", NOT_VALID_PASSWORD_MESSAGE);
    }
  }

  @Override
  public HashMap<String, String> getAllErrors() {
    HashMap<String, String> errorToThrow = new HashMap<String, String>(errors);
    errors.clear();
    return errorToThrow;
  }

  private boolean isNotValidField(String field, String fieldPattern) {
    Pattern pattern = Pattern.compile(fieldPattern);
    Matcher matcher = pattern.matcher(field);
    return !matcher.find();
  }

  private boolean isUsernameTaken(String username) {
    return userRepository.findByUsername(username).isPresent();
  }

  private boolean isEmailTaken(String email) {
    return userRepository.findByEmail(email).isPresent();
  }
}
