package hotels.services;

import hotels.models.User;

import java.util.Optional;

public interface UserService {

  void checkFields(User user);

  void regNewUser(User user);

  Optional<User> findByUsername(String username);
}
