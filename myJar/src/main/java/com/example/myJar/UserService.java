package com.example.myJar;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public UserDTO createUser(CreateUserDTO request)
    {
        User saved = new User(request.getName(), request.getEmail());
        User user = userRepository.save(saved);
        return ConvertToDTO(user);
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
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found with id: "+id));
        if (user ==  null)
        {
            return null;
        }
        return ConvertToDTO(user);
    }

    public Page<UserDTO> getAllUsersPaginated(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return userRepository.findAll(pageable)
                .map(this::ConvertToDTO);
    }

    public List<UserDTO> searchByName (String Keyword)
    {
        return userRepository.searchByName(Keyword)
                .stream()
                .map(this::ConvertToDTO)
                .toList();
    }
}
