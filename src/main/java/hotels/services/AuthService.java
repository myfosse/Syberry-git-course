package hotels.services;

import hotels.payloads.LoginRequest;

import java.util.HashMap;

public interface AuthService {
  HashMap<String, String> authenticateUser(LoginRequest loginRequest);
}
