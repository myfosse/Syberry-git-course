package hotels.controllers;

import hotels.models.Hotel;
import hotels.repositories.HotelRepository;
import hotels.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

  @Autowired
  HotelService hotelService;

  @Autowired
  HotelRepository hotelRepository;

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
