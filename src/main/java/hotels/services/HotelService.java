package hotels.services;

import hotels.models.Hotel;

import java.util.HashMap;
import java.util.List;

public interface HotelService {

  List<HashMap<String, Object>> getSavedItemsFromPage(int page, long id);

  List<HashMap<String, Object>> getOwnedItemsFromPage(int page, long id);

  long getTotalItems();

  HashMap<String, Object> getHotelInfo(Hotel hotel);

  HashMap<String, List<String>> getHotelCharacteristic(Hotel hotel);

  List<HashMap<String, Object>> getAllItemsFromPage(int page);
}
