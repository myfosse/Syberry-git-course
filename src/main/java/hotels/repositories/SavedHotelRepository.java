package hotels.repositories;

import hotels.models.SavedHotel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SavedHotelRepository extends CrudRepository<SavedHotel, Long> {

  List<SavedHotel> findByUsersId(Long id);
}
