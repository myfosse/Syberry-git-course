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
  private final HotelCharacteristicRepository hotelCharacteristicRepository;
  private final HotelCharacteristicGroupRepository hotelCharacteristicGroupRepository;
  private final static int itemsAmountPerPage = 10;

  @Autowired
  public HotelServiceImpl(PricingRepository pricingRepository,
                          HotelFileRepository hotelFileRepository,
                          SavedHotelRepository savedHotelRepository,
                          HotelRepository hotelRepository,
                          HotelCharacteristicRepository hotelCharacteristicRepository,
                          HotelCharacteristicGroupRepository hotelCharacteristicGroupRepository) {
    this.pricingRepository = pricingRepository;
    this.hotelFileRepository = hotelFileRepository;
    this.savedHotelRepository = savedHotelRepository;
    this.hotelRepository = hotelRepository;
    this.hotelCharacteristicRepository = hotelCharacteristicRepository;
    this.hotelCharacteristicGroupRepository = hotelCharacteristicGroupRepository;
  }

  @Override
  public HashMap<String, List<String>> getHotelCharacteristic(Hotel hotel) {
    List<HotelCharacteristicGroup> groups = hotelCharacteristicGroupRepository.findAll();
    HashMap<String, List<String>> characteristics = new HashMap<>();

    for (HotelCharacteristicGroup group: groups) {
      characteristics.put(group.getName(), hotelCharacteristicRepository.getValues(group.getId(), hotel.getId()));
    }
    return characteristics;
  }

  @Override
  public HashMap<String, Object> getHotelInfo(Hotel hotel) {
    return getItem(hotel);
  }

  @Override
  public List<HashMap<String, Object>> getSavedItemsFromPage(int page, long id) {
    List<SavedHotel> savedHotels = savedHotelRepository.findByUsersId(id);
    return mapToSavedResponse(savedHotels, page);
  }

  @Override
  public List<HashMap<String, Object>> getOwnedItemsFromPage(int page, long id) {
    List<Hotel> ownedHotels = hotelRepository.findByOwnerId(id);
    return mapToOwnedResponse(ownedHotels, page);
  }

  private List<HashMap<String, Object>> mapToOwnedResponse(List<Hotel> ownedHotels, int page) {
    List<HashMap<String, Object>> items = new ArrayList<>();
    for (int i = itemsAmountPerPage * (page - 1); i < page * itemsAmountPerPage && i < ownedHotels.size(); i++) {
      items.add(getItem(ownedHotels.get(i)));
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
    item.put("description", hotel.getDescription());
    item.put("address", getLocationInfo(hotel.getLocation()));
    item.put("price", getTotalPrice(hotel));
    item.put("mainPhoto", getMainPhoto(hotel));

    return item;
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

  private HashMap<String, String> getLocationInfo(Location location) {
    HashMap<String, String> address = new HashMap<>();
    address.put("country", location.getCountry());
    address.put("city", location.getCity());
    address.put("address_line_1", location.getAddressLine());
    address.put("zip", location.getZip());
    return address;
  }

  private String getMainPhoto(Hotel hotel) {
    Optional<HotelFile> hotelFile = hotelFileRepository.findMainByHotelId(hotel.getId());
    return hotelFile.map(file -> file.getFiles().getExternalId()).orElse(null);
  }

}
