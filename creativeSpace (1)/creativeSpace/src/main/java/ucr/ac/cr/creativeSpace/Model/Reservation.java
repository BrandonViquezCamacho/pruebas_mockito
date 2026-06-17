package ucr.ac.cr.creativeSpace.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reservations_tb")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "creativeSpace_id", nullable = false, foreignKey = @ForeignKey(name = "fk_reservation_space"))
    private CreativeSpace creativeSpace;

    @ManyToOne
    @JoinColumn(name = "user_email", nullable = false, foreignKey = @ForeignKey(name = "fk_reservation_user"))
    private User user;

    @Column(name = "dateReserved", nullable = false)
    private LocalDate dateReserved;
    @Column(name = "status", nullable = false, length = 20)
    private String status;

    public Reservation() {
    }

    public Reservation(Integer id, CreativeSpace creativeSpace, User user, LocalDate dateReserved, String status) {
        this.id = id;
        this.creativeSpace = creativeSpace;
        this.user = user;
        this.dateReserved = dateReserved;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CreativeSpace getCreativeSpace() {
        return creativeSpace;
    }

    public void setCreativeSpace(CreativeSpace creativeSpace) {
        this.creativeSpace = creativeSpace;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDateReserved() {
        return dateReserved;
    }

    public void setDateReserved(LocalDate dateReserved) {
        this.dateReserved = dateReserved;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
