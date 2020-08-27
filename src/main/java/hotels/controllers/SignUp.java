package hotels.controllers;

import hotels.models.User;
import hotels.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping
public class SignUp {

  @Autowired
  UserService userService;

  @PostMapping("auth/sign-up")
  public Object signUp(@RequestBody User user) {

      userService.checkFields(user);
      userService.regNewUser(user);
      HashMap<String, String> payload = new HashMap<>();
      payload.put("username", user.getUsername());
      return payload;
  }
}
