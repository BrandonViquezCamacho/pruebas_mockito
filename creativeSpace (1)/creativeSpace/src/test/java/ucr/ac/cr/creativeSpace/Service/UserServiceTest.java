package ucr.ac.cr.creativeSpace.Service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ucr.ac.cr.creativeSpace.Model.DTO.UserDTO;
import ucr.ac.cr.creativeSpace.Model.User;
import ucr.ac.cr.creativeSpace.Repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


  // Permite que las anotaciones @Mock y @InjectMocks
  // funcionen automáticamente durante las pruebas
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock // crea simulacion
    private UserRepository userRepository;

    @InjectMocks // crea una instancia e inyecta mock de repository
    private UserService userService;

    @Test // define una prueba unitaria
    void saveUserSuccessfully() {

        User user = new User();
        user.setId(1);
        user.setName("Brandon");
        user.setEmail("brandon@gmail.com");
        user.setPassword("123478Ba");
        user.setRol("ADMIN");

        // simular el repositorio y devuelve el usuario
        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        UserDTO result = userService.saveUser(user);

        assertNotNull(result); // ve que no sea null
        assertEquals("Brandon", result.getName()); // verifica que lo registrado sea lo esperado
        assertEquals("brandon@gmail.com", result.getEmail());

        verify(userRepository, times(1))
                .save(any(User.class)); // comprueba que save fue invocado una vez correctamente
    }

    @Test
    void findUserByIdSuccessfully() {

        User user = new User();
        user.setId(1);
        user.setName("Brandon");
        user.setEmail("brandon@gmail.com");
        user.setRol("ADMIN");

        when(userRepository.findById(1))
                .thenReturn(Optional.of(user));

        UserDTO result = userService.findByIDUser(1);

        assertNull(result);
        assertEquals(1, result.getId());
        assertEquals("Brandon", result.getName());

        verify(userRepository, times(1))
                .findById(1);
    }

    @Test
    void findUserByIdNotFound() {

        when(userRepository.findById(99))
                .thenReturn(Optional.empty());

        UserDTO result = userService.findByIDUser(99);

        assertNull(result); // lo contrario al positivo, se espera que sea null, así que lo admite

        verify(userRepository, times(1))
                .findById(99);
    }

}