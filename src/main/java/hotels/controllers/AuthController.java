package hotels.controllers;

import hotels.models.User;
import hotels.payloads.LoginRequest;
import hotels.payloads.ResetRequest;
import hotels.services.AuthService;
import hotels.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;
  private final UserService userService;

  @Autowired
  public AuthController(AuthService authService, UserService userService) {
    this.authService = authService;
    this.userService = userService;
  }

  @PostMapping("/sign-in")
  public HashMap<String, String> authenticateUser(@RequestBody LoginRequest loginRequest) {
    return authService.authenticateUser(loginRequest);
  }

  @PostMapping("/sign-up")
  public HashMap<String, String> signUp(@RequestBody User user) {
    userService.checkFields(user);
    String notEncodedPassword = user.getPassword();
    userService.regNewUser(user);
    LoginRequest request = userToLoginRequest(user, notEncodedPassword);

    return authService.authenticateUser(request);
  }

  @PostMapping("/password-reset")
  public void passwordReset(@RequestBody ResetRequest resetRequest,
                            HttpServletResponse response) {
    userService.resetPassword(resetRequest, response);
  }

  private LoginRequest userToLoginRequest(User user, String password) {
    LoginRequest loginRequest = new LoginRequest();

    loginRequest.setEmail(user.getEmail());
    loginRequest.setPassword(password);

    return loginRequest;
  }
}
