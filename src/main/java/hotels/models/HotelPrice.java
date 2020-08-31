package hotels.models;

import javax.persistence.*;

@Entity
@Table(name = "hotel_price")
public class HotelPrice {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @OneToOne
  @JoinColumn(name = "hotel_id", referencedColumnName = "id")
  private Hotel hotel;

  private long price;

  private Long pricing_plan_id;

  public Long getPricing_plan_id() {
    return pricing_plan_id;
  }

  public void setPricing_plan_id(Long pricing_plan_id) {
    this.pricing_plan_id = pricing_plan_id;
  }

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

  public long getPrice() {
    return price;
  }

  public void setPrice(long value) {
    this.price = value;
  }
}
