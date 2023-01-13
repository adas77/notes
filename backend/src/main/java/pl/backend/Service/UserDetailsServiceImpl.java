package pl.backend.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pl.backend.Model.User;
import pl.backend.Repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // @Autowired
    // public UserDetailsServiceImpl(HttpServletRequest httpServletRequest) {
    // this.httpServletRequest = httpServletRequest;
    // }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("Could not find user");
        }
        User user = userOptional.get();
        return user;
    }

//     private String getClientIP() {
//         String xfHeader = httpServletRequest.getHeader("X-Forwarded-For");
//         if (xfHeader == null || xfHeader.isEmpty() || !xfHeader.contains(httpServletRequest.getRemoteAddr())) {
//             return httpServletRequest.getRemoteAddr();
//         }
//         // return xfHeader.split(",")[0];
//         // HttpServletRequest request = ( RequestContextHolder.currentRequestAttributes()).
//         // .getRequest();

// // String ip = request.getRemoteAddr();
//         // return xfHeader;
//     }
}
