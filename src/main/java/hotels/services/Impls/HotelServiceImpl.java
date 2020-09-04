package hotels.services.Impls;

import hotels.models.*;
import hotels.repositories.*;
import hotels.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HotelServiceImpl implements HotelService {

  private final PricingRepository pricingRepository;
  private final HotelFileRepository hotelFileRepository;
  private final SavedHotelRepository savedHotelRepository;
  private final HotelRepository hotelRepository;
  private final static int itemsAmountPerPage = 10;

  @Autowired
  public HotelServiceImpl(PricingRepository pricingRepository,
                          HotelFileRepository hotelFileRepository,
                          SavedHotelRepository savedHotelRepository,
                          HotelRepository hotelRepository) {
    this.pricingRepository = pricingRepository;
    this.hotelFileRepository = hotelFileRepository;
    this.savedHotelRepository = savedHotelRepository;
    this.hotelRepository = hotelRepository;
  }

  @Override
  public List<HashMap<String, Object>> getSavedItemsFromPage(int page, long id) {
    List<SavedHotel> savedHotels = savedHotelRepository.findByUsersId(id);
    return mapToSavedResponse(savedHotels, page);
  }

  @Override
  public List<HashMap<String, Object>> getOwnedItemsFromPage(int page, long id) {
    List<Hotel> ownedHotelsNew  = hotelRepository.findByOwnerId(id);
    return mapToOwnedResponse(ownedHotelsNew , page);
  }

  private List<HashMap<String, Object>> mapToOwnedResponse(List<Hotel> ownedHotelsNew , int page) {
    List<HashMap<String, Object>> items = new ArrayList<>();
    for (int i = itemsAmountPerPage * (page - 1); i < page * itemsAmountPerPage && i < ownedHotelsNew .size(); i++) {
      items.add(getItem(ownedHotelsNew .get(i)));
    }
    return items;
  }

  private List<HashMap<String, Object>> mapToSavedResponse(List<SavedHotel> savedHotels, int page) {
    List<HashMap<String, Object>> items = new ArrayList<>();
    for (int i = itemsAmountPerPage * (page - 1); i < page * itemsAmountPerPage && i < savedHotels.size(); i++) {
      items.add(getItem(savedHotels.get(i).getHotel()));
    }
    return items;
  }

  private HashMap<String, Object> getItem(Hotel hotel) {
    HashMap<String, Object> item = new HashMap<>();

    item.put("id", hotel.getId());
    item.put("title", hotel.getTitle());
    item.put("city", hotel.getLocation().getCity());
    item.put("price", getTotalPrice(hotel));
    item.put("photoUrl", getPhotoURL(hotel));

    return item;
  }

  private String getPhotoURL(Hotel hotel) {
    Optional<HotelFile> hotelFile = hotelFileRepository.findMainByHotelId(hotel.getId());
    return hotelFile.map(file -> file.getFiles().getExternalId()).orElse(null);
  }

  private Long getTotalPrice(Hotel hotel) {
    HotelPrice hotelPrice = hotel.getHotelPrice();
    long pricePerDay = hotelPrice.getPrice();
    long days = getDays(hotelPrice.getPricing_plan_id());
    return pricePerDay * days;
  }

  private long getDays(long id) {
    String pricingPlan = pricingRepository.findById(id).get().getType().toString();
    Map<String, Long> pricingPlans = generatePricingPlans();
    return pricingPlans.get(pricingPlan);
  }

  private Map<String, Long> generatePricingPlans() {
    Map<String, Long> pricingPlans = new HashMap<>();
    pricingPlans.put("ONE_DAY", 1L);
    return pricingPlans;
  }
}
