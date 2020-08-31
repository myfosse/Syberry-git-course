package hotels.services.Impls;

import hotels.models.Auth;
import hotels.models.User;
import hotels.payloads.LoginRequest;
import hotels.repositories.AuthRepository;
import hotels.repositories.UserRepository;
import hotels.security.JwtTokenProvider;
import hotels.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider tokenProvider;

  @Autowired
  public AuthServiceImpl(
          AuthenticationManager authenticationManager,
          JwtTokenProvider tokenProvider) {
    this.authenticationManager = authenticationManager;
    this.tokenProvider = tokenProvider;
  }

  @Autowired
  AuthRepository authRepository;

  @Autowired
  UserRepository userRepository;

  @Override
  public HashMap<String, String> authenticateUser(LoginRequest loginRequest) {
    Authentication authentication =
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    HashMap<String, String> tokens = new HashMap<>();

    String jwt = tokenProvider.generateToken(authentication);
    String refreshToken = tokenProvider.generateRefreshToken(loginRequest.getRememberMe());

    loginUser(loginRequest, refreshToken);

    tokens.put("token", jwt);
    tokens.put("refreshToken", refreshToken);

    return tokens;
  }

  private void loginUser(LoginRequest request, String token) {
    String email = request.getEmail();

    User user = userRepository.findByEmail(email).get();
    Optional<Auth> authOptional = authRepository.findByUserId(user.getId());

    if (authOptional.isPresent()) {
      Auth auth = authOptional.get();
      auth.setRefreshToken(token);
      authRepository.save(auth);
    } else {
      Auth newAuth = new Auth();
      newAuth.setRememberMe(request.getRememberMe());
      newAuth.setRefreshToken(token);
      newAuth.setUser(user);
      authRepository.save(newAuth);
    }
  }
}
