package jobnest.amine.com.initilizer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jobnest.amine.com.model.User;
import jobnest.amine.com.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class InitDatabase implements CommandLineRunner {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) {
        List<User> USERS = Arrays.asList(
                new User(1L, "amine", "admin@gmail.com", passwordEncoder.encode("amineamine1"), "ADMIN", null),
                new User(2L, "user", "user@gmail.com", passwordEncoder.encode("user"), "USER", null)
        );
        if (!repository.findAll().isEmpty()) {
            return;
        }
        repository.saveAll(USERS);
        log.info("Database initialized");
    }
}