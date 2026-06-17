package ucr.ac.cr.creativeSpace.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@Entity
@Table(name= "tb_espaciosCreativos")
public class CreativeSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 100, unique = true)
    Integer id;
    @Column(name = "name", nullable = false, length = 100, unique = true)
    String name;
    @Column(name = "location", nullable = false, length = 250)
    String location;
    @Column(name = "type", nullable = false, length = 100)
    String type;
    @Column(name = "price", nullable = false)
    Double price;

    @JsonIgnore
    @OneToMany(mappedBy = "creativeSpace")
    private List<Reservation> listReservation;

    public CreativeSpace() {
    }

    public CreativeSpace(Integer id, String name, String location, String type, Double price) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.type = type;
        this.price = price;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CreativeSpace{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
