package pl.backend.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.backend.Model.User;
import pl.backend.Service.UserService;

@Slf4j
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping()
    public void create(@RequestBody User user) {
        userService.create(user);
        log.info("added");
    }
}
