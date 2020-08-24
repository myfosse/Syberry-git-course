package hotels.services;

import java.util.HashMap;
import java.util.List;

public interface HotelService {

  List<HashMap<String, Object>> getSavedItemsFromPage(int page, long id);

  List<HashMap<String, Object>> getOwnedItemsFromPage(int page, long id);

}
