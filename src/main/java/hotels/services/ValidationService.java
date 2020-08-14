package hotels.services;

import java.util.HashMap;

public interface ValidationService {
  void checkUsername(String username);

  void checkEmail(String email);

  void checkPassword(String password, String username);

  HashMap<String, String> getAllErrors();
}
