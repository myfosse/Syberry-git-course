package hotels.services;

import hotels.models.User;
import hotels.payloads.ResetRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public interface UserService {

  void checkFields(User user);

  void regNewUser(User user);

  Optional<User> findByUsername(String username);

  void resetPassword(ResetRequest resetRequest, HttpServletResponse response);
}
