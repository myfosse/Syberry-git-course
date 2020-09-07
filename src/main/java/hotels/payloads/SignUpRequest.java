package hotels.payloads;

import com.sun.istack.NotNull;

public class SignUpRequest {
  @NotNull private String username;

  @NotNull private String email;

  @NotNull private String password;

  @NotNull private String codeword;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getCodeword() {
    return codeword;
  }

  public void setCodeword(String codeword) {
    this.codeword = codeword;
  }
}
