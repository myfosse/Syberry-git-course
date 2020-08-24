package hotels.repositories;

import hotels.models.HotelCharacteristic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HotelCharacteristicRepository extends CrudRepository<HotelCharacteristic, Long> {
  List<HotelCharacteristic> findValueByHotelIdAndHotelCharacteristicGroupId(long hotelId, long groupId);

  @Query(value = "SELECT hc.value FROM hotel_characteristic hc WHERE hc.group_id = ?1 and hc.hotel_id = ?2", nativeQuery = true)
  List<String> getValues(long groupId, long hotelId);
}
