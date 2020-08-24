package hotels.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "files")
public class File {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "external_id")
  private String externalId;

  @OneToMany(mappedBy = "files")
  private Set<HotelFile> hotelFiles;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getExternalId() {
    return externalId;
  }

  public void setExternalId(String externalId) {
    this.externalId = externalId;
  }

  public Set<HotelFile> getHotelFiles() {
    return hotelFiles;
  }

  public void setHotelFiles(Set<HotelFile> hotelFiles) {
    this.hotelFiles = hotelFiles;
  }
}
