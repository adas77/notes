package pl.backend.Controller;

import java.util.concurrent.TimeUnit;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pl.backend.Auth.AuthRequest;
import pl.backend.Auth.AuthResponse;
import pl.backend.Auth.RegisterRequest;
import pl.backend.Service.AuthService;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest request) throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody AuthRequest request) throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        return ResponseEntity.ok(authService.authenticate(request));
    }

}
