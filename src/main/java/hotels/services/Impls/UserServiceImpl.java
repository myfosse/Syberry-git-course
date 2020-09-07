package hotels.services.Impls;

import hotels.exceptions.ApiRequestException;
import hotels.models.User;
import hotels.payloads.ResetRequest;
import hotels.repositories.UserRepository;
import hotels.security.JwtAuthenticationFilter;
import hotels.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

  private static final String USERNAME_PATTERN = "^[A-z .,-_]{1,60}$";
  private static final String EMAIL_PATTERN = "^[A-z0-9+_.-]+@[A-Za-z0-9.-]+$";
  private static final String PASSWORD_PATTERN = "^(?=.*\\d{1,})(?=.*[a-z]{1,})(?=.*[A-Z]{1,})(?=.*[#?!@$%^&*-]{1,}).{8,64}$";
  private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

  @Autowired UserRepository userRepository;

  @Autowired PasswordEncoder passwordEncoder;

  HashMap<String, String> errors = new HashMap<>();

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder(8);
  }

  @Override
  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  @Override
  public void checkFields(User user) {
    checkUsername(user.getUsername());
    checkEmail(user.getEmail());
    checkPassword(user.getPassword(), user.getUsername());
    if (!errors.isEmpty()) {
      HashMap<String, String> errorsToThrow = new HashMap<>(errors);
      errors.clear();
      throw new ApiRequestException(errorsToThrow);
    }
  }

  @Override
  public void regNewUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setCodeword(passwordEncoder.encode(user.getCodeword()));
    userRepository.save(user);
  }

  @Override
  public void resetPassword(ResetRequest resetRequest, HttpServletResponse response) {
    Optional<User> userOptional = userRepository.findByEmail(resetRequest.getEmail());
    String codeword = resetRequest.getCodeword();

    if (userOptional.isPresent() && passwordEncoder.matches(codeword, userOptional.get().getCodeword())) {
      String newPassword = passwordEncoder.encode(resetRequest.getNewPassword());
      changePassword(userOptional.get(), newPassword);
      logger.info("Password changed successfully");
    } else {
      response.setStatus(401);
      logger.error("Wrong email or codeword");
    }
  }

  private void changePassword(User user, String newPassword) {
    user.setPassword(newPassword);
    userRepository.save(user);
  }

  private void checkUsername(String username) {
    if (isNotValidField(username, USERNAME_PATTERN)) {
      errors.put("username", "Not valid username");
      return;
    }

    if (isUsernameTaken(username)) {
      errors.put("username", "Username already exist");
    }
  }

  private void checkEmail(String email) {
    if (isNotValidField(email, EMAIL_PATTERN)) {
      errors.put("email", "Not valid email");
      return;
    }

    if (isEmailTaken(email)) {
      errors.put("email", "Email already exist");
    }
  }

  private void checkPassword(String password, String username) {
    if (password.matches(username)) {
      errors.put("password", "Password must not match the name");
      return;
    }

    if (isNotValidField(password, PASSWORD_PATTERN)) {
      errors.put("password", "Not valid password");
    }
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
