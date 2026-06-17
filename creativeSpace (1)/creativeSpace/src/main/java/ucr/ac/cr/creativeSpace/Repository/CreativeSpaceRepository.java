package ucr.ac.cr.creativeSpace.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucr.ac.cr.creativeSpace.Model.CreativeSpace;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CreativeSpaceRepository extends JpaRepository<CreativeSpace, Integer>{


}
