package hotels.repositories;

import hotels.models.Auth;
import org.springframework.data.repository.CrudRepository;

public interface AuthRepository extends CrudRepository<Auth, Long> {
  Auth findByRefreshToken(String refreshToken);
}
