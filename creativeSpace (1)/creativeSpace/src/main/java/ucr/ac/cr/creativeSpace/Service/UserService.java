package ucr.ac.cr.creativeSpace.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.creativeSpace.Model.DTO.LoginDTO;
import ucr.ac.cr.creativeSpace.Model.DTO.UserDTO;
import ucr.ac.cr.creativeSpace.Model.User;
import ucr.ac.cr.creativeSpace.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public UserDTO saveUser (User user) {
        return this.convertUserDTO(this.userRepository.save(user));
    }

    public void deleteUser(Integer id) {
        this.userRepository.deleteById(id);
    }



    public UserDTO findByIDUser(Integer id) {
        Optional<User> optional = this.userRepository.findById(id);

        if(optional.isEmpty()){
            return null;
        }

        return this.convertUserDTO(optional.get());
    }

    public UserDTO editUser(Integer id, User userEdit) {
        Optional<User> optional = userRepository.findById(id);

        if(optional.isEmpty()){
            return null;
        }

        User userDB = optional.get();

        userDB.setName(userEdit.getName());
        userDB.setEmail(userEdit.getEmail());
        userDB.setPassword(userEdit.getPassword());
        userDB.setRol(userEdit.getRol());

        return convertUserDTO(
                userRepository.save(userDB)
        );
    }


    public List<UserDTO> findAll() {
        return this.convertListDTO(this.userRepository.findAll());
    }


    //convertidor DTO
    public UserDTO convertUserDTO(User user){
        UserDTO dto =new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setRol(user.getRol());

        return dto;
    }

    //convertidor DTO
    public List<UserDTO> convertListDTO(List<User> listUser){
        List<UserDTO> listDTO=new ArrayList<>();
        for (User user : listUser){
            listDTO.add(this.convertUserDTO(user));
        }
        return listDTO;
    }

    //findBy
    public List<UserDTO> findByName (String name){
        return this.convertListDTO(this.userRepository.findByName(name));
    }

    public List<User> findByRol (String rol){
        return this.userRepository.findByRol(rol);
    }

    public List<User> findAllByOrderByNameAsc (){
        return this.userRepository.findAllByOrderByNameAsc();
    }


    public List<User> buscarRoles (String rol){
        return this.userRepository.buscarRol(rol);
    }


    public User findByEmailAndPassword (String email, String password){
        return this.userRepository.findByEmailAndPassword(email, password);
    }

    public  User userLogin (String email, String password){

        return this.userRepository.userLogin(email, password);
    }

}
