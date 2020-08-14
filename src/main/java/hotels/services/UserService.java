package hotels.services;

import hotels.models.User;

public interface UserService {

  void checkFields(User user);

  void regNewUser(User user);
}
