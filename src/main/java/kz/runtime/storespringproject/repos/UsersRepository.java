package kz.runtime.storespringproject.repos;


import kz.runtime.storespringproject.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query(value = "SELECT * from users where email = :email", nativeQuery = true)
    Optional<Users> findUsersByUsername(@Param("email") String username);

    @Query(value = "SELECT * FROM users WHERE id = :id", nativeQuery = true)
    Optional<Users> findUsersById(@Param("id") Long id);

}
