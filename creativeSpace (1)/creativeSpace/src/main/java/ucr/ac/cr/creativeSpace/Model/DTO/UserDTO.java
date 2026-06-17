package ucr.ac.cr.creativeSpace.Model.DTO;

public class UserDTO {
    private Integer id;
    private String name;
    private String email;
    private String rol;

    public UserDTO() {
    }

    public UserDTO(Integer id, String name, String email, String rol) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.rol = rol;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
