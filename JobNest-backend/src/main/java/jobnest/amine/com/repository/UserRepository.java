package jobnest.amine.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jobnest.amine.com.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
}
