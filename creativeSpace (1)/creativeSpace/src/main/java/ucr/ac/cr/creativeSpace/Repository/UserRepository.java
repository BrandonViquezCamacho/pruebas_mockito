package ucr.ac.cr.creativeSpace.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ucr.ac.cr.creativeSpace.Model.User;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByName (String name);

//    User findByEmail(String email);
    User findByEmailAndPassword (String email, String password);
    List<User> findByRol (String rol);

    List <User> findAllByOrderByNameAsc();
    //Ordenamiento / findBy = busca / TrueOrderBy = ordena / Asc, Desc = de abajo arriba o viceversa


    //JPQL = metodo de onsula bases de datos
    @Query("SELECT u FROM User u WHERE u.rol = :rol")
    List <User> buscarRol (@Param("rol")String rol);


    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    User userLogin (@Param("email")String email, @Param("password")String password);
}
