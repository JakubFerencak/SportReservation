package sk.kasv.ferencak.SportReservation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.kasv.ferencak.SportReservation.Entity.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {
}