package bodnar.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Location {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "country")
    private String country;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "street")
    private String street;
    @Basic
    @Column(name = "building_number")
    private String buildingNumber;
    @OneToMany(mappedBy = "location")
    private List<Teritory> teritories;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(id, location.id) && Objects.equals(country, location.country) && Objects.equals(city, location.city) && Objects.equals(street, location.street) && Objects.equals(buildingNumber, location.buildingNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, street, buildingNumber);
    }

    public List<Teritory> getTeritories() {
        return teritories;
    }

    public void setTeritories(List<Teritory> teritories) {
        this.teritories = teritories;
    }
}
