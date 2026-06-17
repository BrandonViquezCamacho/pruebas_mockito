package ucr.ac.cr.creativeSpace.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucr.ac.cr.creativeSpace.Model.Reservation;
import ucr.ac.cr.creativeSpace.Model.User;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findByUserId(Integer id);
    List<Reservation> findByCreativeSpaceId(Integer id);
}
