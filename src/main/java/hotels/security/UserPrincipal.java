package hotels.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hotels.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserPrincipal implements UserDetails {
  private final Long id;
  private final String username;
  @JsonIgnore private final String email;
  private final String password;

  public UserPrincipal(Long id, String username, String email, String password) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public static UserPrincipal create(User user) {
    return new UserPrincipal(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
