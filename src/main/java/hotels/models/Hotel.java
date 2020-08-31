package hotels.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "hotels")
public class Hotel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String title;

  @OneToOne
  @JoinColumn(name = "location_id", referencedColumnName = "id")
  private Location location;

  @OneToOne(mappedBy = "hotel", cascade = CascadeType.ALL)
  private HotelPrice hotelPrice;

  @OneToMany(mappedBy = "hotel")
  private Set<HotelFile> hotelFiles;

  public Set<HotelFile> getHotelFiles() {
    return hotelFiles;
  }

  public void setHotelFiles(Set<HotelFile> hotelFiles) {
    this.hotelFiles = hotelFiles;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public HotelPrice getHotelPrice() {
    return hotelPrice;
  }

  public void setHotelPrice(HotelPrice hotelPrice) {
    this.hotelPrice = hotelPrice;
  }
}
