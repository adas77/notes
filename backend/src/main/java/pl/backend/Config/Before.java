// package pl.backend.Config;

// import java.util.Arrays;
// import java.util.HashSet;
// import java.util.Set;

// import org.springframework.boot.context.event.ApplicationReadyEvent;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.event.EventListener;

// import lombok.AllArgsConstructor;
// import pl.backend.Model.EUserRole;
// import pl.backend.Model.Role;
// import pl.backend.Model.User;
// import pl.backend.Service.UserService;

// @Configuration
// @AllArgsConstructor
// public class Before {

//     private final UserService userService;

//     @EventListener(ApplicationReadyEvent.class)
//     public void loadUsers() {
//         Role r = new Role(EUserRole.ROLE_ADMIN);
//         Role u = new Role(EUserRole.ROLE_ADMIN);
//         final Set<Role> roles1 = new HashSet<Role>(Arrays.asList(r));
//         final Set<Role> roles2 = new HashSet<Role>(Arrays.asList(r, u));

//         User basic = new User("hello@gmail.com", "aaa", "aaa", roles1);
//         User admin = new User("admin@gmail.com", "bbb", "bbb", roles2);

//         userService.create(basic);
//         userService.create(admin);
//     }
// }
