package ucr.ac.cr.creativeSpace.Service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ucr.ac.cr.creativeSpace.Model.User;
import ucr.ac.cr.creativeSpace.Repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @Test
    void saveUserSuccessfully() {

        User user = new User();

        user.setName("Brandon");
        user.setEmail("brandon@gmail.com");
        user.setPassword("1234");

        when(repository.save(any(User.class)))
                .thenReturn(user);

        User result = repository.save(user);

        assertNotNull(result);

        verify(repository).save(any(User.class));
    }
}