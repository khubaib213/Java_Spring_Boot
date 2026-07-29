package com.example.myJar;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public UserDTO create(@Valid @RequestBody CreateUserDTO user){

        return userService.createUser(user);
    }

    @GetMapping("/all")
    public List<UserDTO> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable int id)
    {
        return userService.findById(id);
    }

    @GetMapping("/search")
    public List<UserDTO> search (@RequestParam String keyword)
    {
        return userService.searchByName(keyword);
    }

    @GetMapping("/domain")
    public List<UserDTO> findByDomain(@RequestParam String domain)
    {
        return userService.findByEmailDomain(domain);
    }

    @GetMapping("/all/paginated")
    public Page<UserDTO> getAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return userService.getAllUsersPaginated(page, size, sortBy);
    }
}
