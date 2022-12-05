package pl.backend.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import pl.backend.Model.User;
import pl.backend.Service.UserService;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping("/create")
    public void create(@RequestBody User user) {
        userService.create(user);
    }
}
