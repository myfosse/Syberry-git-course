package hotels.models;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
@Table(name = "hotel_file")
public class HotelFile {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "hotel_id")
  private Hotel hotel;

  @ManyToOne
  @JoinColumn(name = "file_id")
  private File files;

  @Value("${some.key:false}")
  @Column(name = "is_main")
  private boolean isMain;

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

  public File getFiles() {
    return files;
  }

  public void setFiles(File files) {
    this.files = files;
  }

  public boolean isMain() {
    return isMain;
  }

  public void setMain(boolean main) {
    isMain = main;
  }
}
