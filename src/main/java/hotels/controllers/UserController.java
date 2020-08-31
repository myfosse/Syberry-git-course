package hotels.controllers;

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

  @Autowired
  HotelService hotelService;

  @GetMapping
  public HashMap<String, String> homepage(@CurrentUser UserPrincipal currentUser) {
    HashMap<String, String> username = new HashMap<>();
    username.put("username", currentUser.getUsername());
    return username;
  }

  @GetMapping("/saved-hotels")
  public HashMap<String, Object> savedHotels(@RequestParam(value = "pagenum") final int pageNum) {

    HashMap<String, Object> response = new HashMap<>();

    response.put("items", hotelService.getItemsFromPage(pageNum));
    response.put("pageNumber", pageNum);
    response.put("totalItems", hotelService.getTotalItems());

    return response;
  }

  @GetMapping("/hotels")
  public HashMap<String, Object> featuredHotels(@RequestParam(value = "pagenum") final int pageNum) {

    HashMap<String, Object> response = new HashMap<>();

    response.put("items", hotelService.getItemsFromPage(pageNum));
    response.put("pageNumber", pageNum);
    response.put("totalItems", hotelService.getTotalItems());

    return response;
  }
}
