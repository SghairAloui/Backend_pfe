package Pfe.module.users.Repository;

import Pfe.module.users.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 static boolean existByEmail(String email) {
     return false;
 }

 Optional<Object> findByEmail(String email);
}