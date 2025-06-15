package sk.kasv.ferencak.SportReservation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.kasv.ferencak.SportReservation.Entity.Reservation;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findByUserId(int userId);
    List<Reservation> findByActivityId(int activityId);
}