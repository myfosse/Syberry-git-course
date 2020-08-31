package hotels.services;

import java.util.HashMap;
import java.util.List;

public interface HotelService {
  long getTotalItems();

  List<HashMap<String, Object>> getItemsFromPage(int page);
}
