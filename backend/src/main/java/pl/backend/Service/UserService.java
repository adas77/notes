package pl.backend.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pl.backend.Model.User;
import pl.backend.Repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void create(User user) {
        // var id = user.getId();
        // Optional<User> userOptional = userRepository.findById(id);
        // if (userOptional.isPresent()) {
        // throw new IllegalStateException(String.format("User with id=%d already
        // exists", id));
        // }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalStateException(String.format("User with email=%d already exists", user.getEmail()));
        } else if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalStateException(String.format("User with username=%d already exists", user.getUsername()));
        }
        // Set<Role> roles = user.getRoles();
        // for (Role role : roles) {
        // roleRepository.save(role);
        // }
        userRepository.save(user);
    }
}
