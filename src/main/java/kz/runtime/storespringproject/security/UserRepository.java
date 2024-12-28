package kz.runtime.storespringproject.security;

import kz.runtime.storespringproject.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String email);
}
