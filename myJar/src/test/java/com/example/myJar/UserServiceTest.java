package com.example.myJar;

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
}