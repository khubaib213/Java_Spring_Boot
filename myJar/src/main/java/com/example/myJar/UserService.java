package com.example.myJar;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

    private UserDTO ConvertToDTO(User users)
    {
        return new UserDTO(
                users.getId(),
                users.getName(),
                users.getEmail()
        );
    }

    public UserDTO createUser(User user)
    {
        User saved = userRepository.save(user);
        return ConvertToDTO(saved);
    }

    public List<UserDTO> getAllUsers ()
    {
        return userRepository.findAll()
                .stream()
                .map(this::ConvertToDTO)
                .toList();
    }

    public UserDTO findById(int id)
    {
        User user = userRepository.findById(id).orElse(null);
        if (user ==  null)
        {
            return null;
        }
        return ConvertToDTO(user);
    }
}
