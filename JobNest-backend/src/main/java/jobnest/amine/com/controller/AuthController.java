package jobnest.amine.com.controller;

import jakarta.validation.Valid;
import jobnest.amine.com.dto.Login;
import jobnest.amine.com.dto.Register;
import jobnest.amine.com.model.User;
import jobnest.amine.com.security.AuthResponse;
import jobnest.amine.com.service.AuthService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody Register register){
        return ResponseEntity.ok(authService.register(register));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody Login login){
        Optional<User> user = authService.login(login);
        if (user.isPresent()){
            User presentUser = user.get();
            return ResponseEntity.ok(new AuthResponse(presentUser.getId(), presentUser.getUsername(), presentUser.getRole()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}