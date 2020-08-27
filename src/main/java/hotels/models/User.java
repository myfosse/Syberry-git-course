package hotels.models;

import org.springframework.beans.factory.annotation.Value;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String username;

  private String email;

  private String password;

  @Value("${some.key:false}")
  private boolean rememberMe;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  private Auth auth;

  public boolean getRememberMe() {
    return rememberMe;
  }

  public void setRememberMe(boolean rememberMe) {
    this.rememberMe = rememberMe;
  }

  public Long getId() {
    return id;
  }

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
}
