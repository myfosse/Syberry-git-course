package hotels.services.Impls;

import hotels.models.Hotel;
import hotels.models.HotelFile;
import hotels.models.Pricing;
import hotels.models.Type;
import hotels.repositories.HotelFileRepository;
import hotels.repositories.HotelRepository;
import hotels.repositories.PricingRepository;
import hotels.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HotelServiceImpl implements HotelService {

  private final HotelRepository hotelRepository;
  private final PricingRepository pricingRepository;
  private final HotelFileRepository hotelFileRepository;

  @Autowired
  public HotelServiceImpl(HotelRepository hotelRepository,
                          PricingRepository pricingRepository,
                          HotelFileRepository hotelFileRepository) {
    this.hotelRepository = hotelRepository;
    this.pricingRepository = pricingRepository;
    this.hotelFileRepository = hotelFileRepository;
  }

  @Override
  public long getTotalItems() {
    return hotelRepository.count();
  }

  @Override
  public List<HashMap<String, Object>> getItemsFromPage(int page) {
    List<Hotel> hotels = hotelRepository.findAll();
    return convertHotelsToItems(hotels, page);
  }

  private List<HashMap<String, Object>> convertHotelsToItems(List<Hotel> hotels, int page) {
    List<HashMap<String, Object>> items = new ArrayList<>();
    for (int i = 10 * (page - 1); i < page * 10 && i < hotelRepository.count(); i++) {
      Hotel hotel = hotels.get(i);
      HashMap<String, Object> item = new HashMap<>();

      item.put("id", hotel.getId());
      item.put("title", hotel.getTitle());
      item.put("city", hotel.getLocation().getCity());
      item.put("price", getTotalPrice(hotel));
      item.put("photoUrl", getPhotoURL(hotel));

      items.add(item);
    }
    return items;
  }

  private String getPhotoURL(Hotel hotel) {
    Optional<HotelFile> hotelFile = hotelFileRepository.findByHotelIdAndIsMainIsTrue(hotel.getId());
    return hotelFile.map(file -> file.getFiles().getExternalId()).orElse(null);
  }

  private Long getTotalPrice(Hotel hotel) {
    long pricePerDay = hotel.getHotelPrice().getPrice();
    long days = getDays(hotel.getHotelPrice().getPricing_plan_id());
    return pricePerDay * days;
  }

  private long getDays(Long id) {
    Pricing pricing = pricingRepository.findById(id).get();
    Type type = pricing.getType();
    if (type.toString().equals("ONE_DAY")) return 1;
    return 0;
  }
}
