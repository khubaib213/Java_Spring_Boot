package com.example.myJar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

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
        logger.info("Creating User with email:{}", request.getEmail());
        User saved = new User(request.getName(), request.getEmail());
        User user = userRepository.save(saved);
        logger.info("User created successfully with id: {}", user.getId());
        return ConvertToDTO(user);
    }

    public List<UserDTO> getAllUsers ()
    {
        logger.info("Fetching all Users");
        return userRepository.findAll()
                .stream()
                .map(this::ConvertToDTO)
                .toList();
    }

    public UserDTO findById(int id) {
        logger.info("Fetching user with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("User not found with id: {}", id);
                    return new ResourceNotFoundException("User not found with id: " + id);
                });
        return ConvertToDTO(user);
    }

    public Page<UserDTO> getAllUsersPaginated(int page, int size, String sortBy) {
        logger.info("Dteching all the User -- page:{}, size:{}, sortBy:{}", page, size, sortBy);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return userRepository.findAll(pageable)
                .map(this::ConvertToDTO);
    }

    public List<UserDTO> searchByName (String Keyword)
    {
        logger.info("Searching Users by keyword :{}", Keyword);
        return userRepository.searchByName(Keyword)
                .stream()
                .map(this::ConvertToDTO)
                .toList();
    }


    public List<UserDTO> findByEmailDomain (String domain)
    {
        return  userRepository.findByEmailDomain(domain)
                .stream()
                .map(this::ConvertToDTO)
                .toList();
    }
}
