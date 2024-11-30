package kz.runtime.storespringproject.repos;

import kz.runtime.storespringproject.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query(value = "SELECT * from users u where u.email = :email", nativeQuery = true)
    Users findUserByName(@Param("email") String username);

}
