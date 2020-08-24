package hotels.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "pricing")
public class Pricing {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Enumerated(EnumType.STRING)
  private Type type;

  @OneToMany(mappedBy = "pricing_plan_id")
  private Set<HotelPrice> hotelPrice;

  public Set<HotelPrice> getHotelPrice() {
    return hotelPrice;
  }

  public void setHotelPrice(Set<HotelPrice> hotelPrice) {
    this.hotelPrice = hotelPrice;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }
}
