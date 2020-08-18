package hotels.controllers;

import hotels.security.CurrentUser;
import hotels.security.UserPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping
public class HomePageController {

  @GetMapping("/users/me")
  public HashMap<String, String> homepage(@CurrentUser UserPrincipal currentUser) {
    HashMap<String, String> username = new HashMap<>();
    username.put("username", currentUser.getUsername());
    return username;
  }
}
