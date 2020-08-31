package hotels.repositories;

import hotels.models.Auth;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthRepository extends CrudRepository<Auth, Long> {
  Auth findByRefreshToken(String refreshToken);

  Optional<Auth> findByUserId(Long id);
}
