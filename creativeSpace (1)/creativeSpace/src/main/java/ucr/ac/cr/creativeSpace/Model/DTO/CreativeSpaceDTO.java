package ucr.ac.cr.creativeSpace.Model.DTO;

public class CreativeSpaceDTO {

    private Integer id;
    private String name;
    private String location;
    private Double price;
    private String type;

    public CreativeSpaceDTO() {
    }

    public CreativeSpaceDTO(Integer id, String name, String location, Double price, String type) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.price = price;
        this.type = type;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
