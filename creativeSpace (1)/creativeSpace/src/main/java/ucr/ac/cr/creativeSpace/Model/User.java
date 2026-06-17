package ucr.ac.cr.creativeSpace.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


import java.util.List;


@Entity
@Table(name= "tb_usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "name", nullable = false, length = 100)
    String name;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$",
            message = "Debe ser un correo @gmail.com"
    )
    String email;


    @Column(name = "password", nullable = false, length = 250)
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[0-9]).{8,}$",
            message = "La contraseña debe tener mínimo 8 caracteres, una mayúscula y un número"
    )
    String password;

    @Column(name = "rol", nullable = false, length = 20)
    String rol;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Reservation> listReservation; //nuevo o modificado, revisar en pryt

    public User() {
    }

    public User(Integer id, String name, String email, String password, String rol) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
