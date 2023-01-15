package pl.backend.Service;

import java.time.LocalDateTime;
import java.util.List;

// import javax.management.relation.Role;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
//import pl.backend.Model.EUserRole;
//import pl.backend.Model.Role;
import pl.backend.Model.User;
import pl.backend.Repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void create(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalStateException(String.format("User with email=%s already exists", user.getEmail()));
        } else if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalStateException(String.format("User with username=%s already exists", user.getUsername()));
        }

        // Role r = new Role(EUserRole.ROLE_USER);
        // Set<Role> roles = new HashSet<Role>(Arrays.asList(r));
        // User newUser = new User(user.getEmail(), user.getUsername(),
        // passwordEncoder.encode(user.getPassword()), roles);
        User newUser = new User(user.getEmail(), user.getUsername(), passwordEncoder.encode(user.getPassword()));
        newUser.setDateOfSignUp(LocalDateTime.now());
        // user.setPassword(passwordEncoder.encode(user.getPassword()));
        // user.addRole(new Role(EUserRole.ROLE_USER));
        userRepository.save(newUser);
    }
}
