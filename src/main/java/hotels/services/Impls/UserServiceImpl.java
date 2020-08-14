package hotels.services.Impls;

import hotels.exceptions.ApiRequestException;
import hotels.models.User;
import hotels.repositories.UserRepository;
import hotels.services.UserService;
import hotels.services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  ValidationService validationService;

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder(8);
  }

  @Override
  public void checkFields(User user) {
    validationService.checkUsername(user.getUsername());
    validationService.checkEmail(user.getEmail());
    validationService.checkPassword(user.getPassword(), user.getUsername());
    HashMap<String, String> errors = validationService.getAllErrors();

    if (!errors.isEmpty()) {
      throw new ApiRequestException(errors);
    }
  }

  @Override
  public void regNewUser(User user) {
    User newUser = new User();
    newUser.setUsername(user.getUsername());
    newUser.setEmail(user.getEmail());
    newUser.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(newUser);
  }
}
