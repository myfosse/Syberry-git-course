package hotels.controllers;

import hotels.repositories.HotelRepository;
import hotels.repositories.SavedHotelRepository;
import hotels.security.CurrentUser;
import hotels.security.UserPrincipal;
import hotels.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/users/me")
public class UserController {

  HotelService hotelService;
  SavedHotelRepository savedHotelRepository;
  HotelRepository hotelRepository;

  @Autowired
  public UserController(HotelService hotelService,
                        SavedHotelRepository savedHotelRepository,
                        HotelRepository hotelRepository) {
    this.hotelService = hotelService;
    this.savedHotelRepository = savedHotelRepository;
    this.hotelRepository = hotelRepository;
  }

  @GetMapping
  public HashMap<String, String> homepage(@CurrentUser UserPrincipal currentUser) {
    HashMap<String, String> username = new HashMap<>();
    username.put("username", currentUser.getUsername());
    return username;
  }

  @GetMapping("/saved-hotels")
  public HashMap<String, Object> savedHotels(@RequestParam(value = "pagenum") final int pageNum,
                                             @CurrentUser UserPrincipal currentUser) {

    HashMap<String, Object> response = new HashMap<>();
    long id = currentUser.getId();

    response.put("items", hotelService.getSavedItemsFromPage(pageNum, id));
    response.put("pageNumber", pageNum);
    response.put("totalItems", savedHotelRepository.findByUsersId(id).size());

    return response;
  }

  @GetMapping("/hotels")
  public HashMap<String, Object> featuredHotels(@RequestParam(value = "pagenum") final int pageNum,
                                                @CurrentUser UserPrincipal currentUser) {

    HashMap<String, Object> response = new HashMap<>();
    long id = currentUser.getId();

    response.put("items", hotelService.getOwnedItemsFromPage(pageNum, id));
    response.put("pageNumber", pageNum);
    response.put("totalItems", hotelRepository.findByOwnerId(id).size());

    return response;
  }
}
