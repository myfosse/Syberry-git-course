package hotels.models;

import javax.persistence.*;

@Entity
@Table(name = "hotel_characteristic")
public class HotelCharacteristic {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "hotel_id")
  private Hotel hotel;

  @ManyToOne
  @JoinColumn(name = "group_id")
  private HotelCharacteristicGroup hotelCharacteristicGroup;

  private String value;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Hotel getHotel() {
    return hotel;
  }

  public void setHotel(Hotel hotel) {
    this.hotel = hotel;
  }

  public HotelCharacteristicGroup getHotelCharacteristicGroup() {
    return hotelCharacteristicGroup;
  }

  public void setHotelCharacteristicGroup(HotelCharacteristicGroup hotelCharacteristicGroup) {
    this.hotelCharacteristicGroup = hotelCharacteristicGroup;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
