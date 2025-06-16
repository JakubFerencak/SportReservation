package sk.kasv.ferencak.SportReservation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.kasv.ferencak.SportReservation.Entity.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}