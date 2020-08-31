package hotels.controllers;

import hotels.payloads.LoginRequest;
import hotels.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping
public class SignInController {

  @Autowired
  AuthService authService;

  @PostMapping("/auth/sign-in")
  public HashMap<String, String> authenticateUser(@RequestBody LoginRequest loginRequest) {
    return authService.authenticateUser(loginRequest);
  }
}
