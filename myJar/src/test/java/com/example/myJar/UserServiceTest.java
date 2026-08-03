package com.example.myJar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest
{
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User fakeUser;

    @BeforeEach
    void setUp (){
        fakeUser = new User("Ali", "ali@gmail.com");
    }

    @Test
    void getAllUsers_ShouldReturnListOfUserDTOs()
    {
        when(userRepository.findAll()).thenReturn(List.of(fakeUser));

        List<UserDTO> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Ali", result.get(0).getName());

        verify(userRepository, times(1)).findAll();

    }

    @Test
    void findById_ShouldReturnUserDTO_whenUserExists()
    {
        when(userRepository.findById(1)).thenReturn(Optional.of(fakeUser));

        UserDTO result = userService.findById(1);

        assertNotNull(result);
        assertEquals("Ali", result.getName());
        assertEquals("ali@gmail.com", result.getEmail());
    }


    @Test
    void findById_ShouldThrowException_WhenUserNotFound()
    {
        when(userRepository.findById(999)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
        {
            userService.findById(999);
        });
    }

    @Test
    void createUser_ShouldSaveAndReturnUserDTO()
    {
        CreateUserDTO request = new CreateUserDTO("Ali", "ali@gmail.com");
        when(userRepository.save(any(User.class))).thenReturn(fakeUser);

        UserDTO result = userService.createUser(request);

        assertNotNull(result);
        assertEquals("Ali", result.getName());


        verify(userRepository, times(1)).save(any(User.class));
    }
}