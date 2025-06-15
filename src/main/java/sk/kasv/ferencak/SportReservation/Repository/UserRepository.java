package sk.kasv.ferencak.SportReservation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.kasv.ferencak.SportReservation.Entity.User;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}