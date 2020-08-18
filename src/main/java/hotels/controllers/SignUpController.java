package hotels.controllers;

import hotels.models.User;
import hotels.payloads.LoginRequest;
import hotels.services.AuthService;
import hotels.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping
public class SignUpController {

  @Autowired
  UserService userService;

  @Autowired
  AuthService authService;

  @PostMapping("auth/sign-up")
  public HashMap<String, String> signUp(@RequestBody User user) {
    userService.checkFields(user);
    String notEncodedPassword = user.getPassword();
    userService.regNewUser(user);
    LoginRequest request = userToLoginRequest(user, notEncodedPassword);

    return authService.authenticateUser(request);
  }

  private LoginRequest userToLoginRequest(User user, String password) {
    LoginRequest loginRequest = new LoginRequest();

    loginRequest.setEmail(user.getEmail());
    loginRequest.setPassword(password);

    return loginRequest;
  }
}
