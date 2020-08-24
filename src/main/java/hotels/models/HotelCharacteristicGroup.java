package hotels.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "hotel_characteristic_groups")
public class HotelCharacteristicGroup {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  private String slug;

  @OneToMany(mappedBy = "hotelCharacteristicGroup")
  private Set<HotelCharacteristic> hotelCharacteristics;

  public Set<HotelCharacteristic> getHotelCharacteristics() {
    return hotelCharacteristics;
  }

  public void setHotelCharacteristics(Set<HotelCharacteristic> hotelCharacteristics) {
    this.hotelCharacteristics = hotelCharacteristics;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }
}
