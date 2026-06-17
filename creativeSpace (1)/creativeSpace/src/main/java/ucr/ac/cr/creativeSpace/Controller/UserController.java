package ucr.ac.cr.creativeSpace.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.creativeSpace.Model.DTO.LoginDTO;
import ucr.ac.cr.creativeSpace.Model.DTO.UserDTO;
import ucr.ac.cr.creativeSpace.Model.User;
import ucr.ac.cr.creativeSpace.Service.UserService;

import java.util.HashMap;
import java.util.Map;


@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService services;


    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.services.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findByIDUser(@PathVariable Integer id) {
        UserDTO dto= this.services.findByIDUser(id);
        if(dto== null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró");
        }
        return ResponseEntity.ok(dto);
    }


    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@Validated @RequestBody User user, BindingResult result) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(services.saveUser(user));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editUser(@Validated @PathVariable Integer id, @RequestBody User user, BindingResult result) {
        if(result.hasErrors()){
            Map<String,String> errors=new HashMap<>();
            for(FieldError error : result.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        UserDTO userEditDTO = this.services.findByIDUser(id);

        if(userEditDTO== null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no existente");
        }
        return ResponseEntity.ok(this.services.editUser(id, user));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete_user(@PathVariable Integer id) {

        UserDTO dto= this.services.findByIDUser(id);

        if(dto== null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe, no se pudo borrar");
        }

        this.services.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/name/{name}")
    public ResponseEntity <?> findByName (@PathVariable String name){
        if(this.services.findByName(name).isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontraron resultados con el nombre");
        }
        return ResponseEntity.ok(this.services.findByName(name));
    }

    @GetMapping("/rol/{rol}")
    public ResponseEntity <?> findByRol (@PathVariable String rol){
        if(this.services.findByRol(rol).isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontraron resultados con el rol");
        }
        return ResponseEntity.ok(this.services.findByRol(rol));
    }

    @GetMapping("/order")
    public ResponseEntity<?>findAllByOrderByNameAsc (){
        return ResponseEntity.ok(this.services.findAllByOrderByNameAsc());
    }

    @GetMapping("/listUser/{rol}")
    public ResponseEntity<?> buscarRoles(@PathVariable String rol){
        return ResponseEntity.ok(this.services.buscarRoles(rol));
    }

    @GetMapping("/findUser/{email}/{password}")
    public ResponseEntity<?> findByEmailAndPassword (@PathVariable String email, @PathVariable String password) {
        return ResponseEntity.ok(this.services.findByEmailAndPassword(email, password));
    }



    //no tienen validaciones correctas, añadirlas
    @PostMapping("/login")
    public ResponseEntity<?> userLogin (@Validated @RequestBody LoginDTO login, BindingResult result){

        if(result.hasErrors()){
            Map<String,String> errors=new HashMap<>();
            for(FieldError error : result.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        User user =this.services.userLogin(login.getEmail(), login.getPassword());

        if(user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas.");
        }

        return ResponseEntity.ok("Usuario "+user.getName()+" logeado");
    }

}
