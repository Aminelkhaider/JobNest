package jobnest.amine.com.service;

import java.util.Optional;

import jobnest.amine.com.dto.Login;
import jobnest.amine.com.dto.Register;
import jobnest.amine.com.model.User;
import jobnest.amine.com.security.AuthResponse;

public interface AuthService {
    AuthResponse register(Register register);
    Optional<User> login(Login login);
}
