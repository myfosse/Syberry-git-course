package hotels.controllers;

import hotels.models.Hotel;
import hotels.repositories.HotelRepository;
import hotels.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

  @Autowired
  HotelService hotelService;

  @Autowired
  HotelRepository hotelRepository;

  @GetMapping()
  public HashMap<String, Object> dashboard(@RequestParam(value = "pagenum") final int pageNum) {

    HashMap<String, Object> response = new HashMap<>();
    response.put("list", hotelService.getAllItemsFromPage(pageNum));
    response.put("pageNumber", pageNum);
    response.put("totalItems", hotelService.getTotalItems());
    return response;
  }

  @GetMapping("/{id}")
  public HashMap<String, Object> hotel(@PathVariable long id) {
    Hotel hotel = hotelRepository.findById(id).get();
    return hotelService.getHotelInfo(hotel);
  }

  @GetMapping("/{id}/characteristics")
  public HashMap<String, List<String>> hotelCharacteristic(@PathVariable long id) {
    Hotel hotel = hotelRepository.findById(id).get();
    return hotelService.getHotelCharacteristic(hotel);
  }
}
