package hotels.controllers;

import hotels.models.Auth;
import hotels.models.User;
import hotels.repositories.AuthRepository;
import hotels.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
@RequestMapping
public class RefreshController {

  @Autowired AuthRepository authRepository;

  private final JwtTokenProvider tokenProvider;

  @Autowired
  public RefreshController(JwtTokenProvider tokenProvider) {
    this.tokenProvider = tokenProvider;
  }

  @PostMapping("/auth/refresh")
  public HashMap<String, String> refreshToken(
      @RequestBody Auth body, HttpServletRequest request, HttpServletResponse response) {
    String refreshToken = body.getRefreshToken();

    if (tokenProvider.validateToken(refreshToken, request)) {
      Auth auth = authRepository.findByRefreshToken(refreshToken);
      User user = auth.getUser();
      String jwt = tokenProvider.refreshToken(user);

      HashMap<String, String> tokens = new HashMap<>();
      tokens.put("token", jwt);
      tokens.put("refreshToken", refreshToken);

      return tokens;
    } else {
      response.setStatus(401);
      HashMap<String, String> error = new HashMap<>();
      error.put("error", "Token is expired");

      return error;
    }
  }
}
