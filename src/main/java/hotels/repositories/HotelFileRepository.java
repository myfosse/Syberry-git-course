package hotels.repositories;

import hotels.models.HotelFile;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HotelFileRepository extends CrudRepository<HotelFile, Long> {

  Optional<HotelFile> findByHotelIdAndIsMainIsTrue(Long id);
}
