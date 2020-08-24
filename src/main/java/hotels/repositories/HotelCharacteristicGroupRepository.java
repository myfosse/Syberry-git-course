package hotels.repositories;

import hotels.models.HotelCharacteristicGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HotelCharacteristicGroupRepository extends CrudRepository<HotelCharacteristicGroup, Long> {

  List<HotelCharacteristicGroup> findAll();
}
