package sk.kasv.ferencak.SportReservation.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.kasv.ferencak.SportReservation.Entity.Activity;
import sk.kasv.ferencak.SportReservation.Entity.Reservation;
import sk.kasv.ferencak.SportReservation.Entity.User;
import sk.kasv.ferencak.SportReservation.Repository.ActivityRepository;
import sk.kasv.ferencak.SportReservation.Repository.ReservationRepository;
import sk.kasv.ferencak.SportReservation.Repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ActivityRepository activityRepository;


    public static record ReservationRequest(int userId, int activityId, LocalDateTime reservationTime) {}


    @PostMapping("/reservations")
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationRequest request) {

        User user = userRepository.findById(request.userId()).orElse(null);
        Activity activity = activityRepository.findById(request.activityId()).orElse(null);

        if (user == null || activity == null) {
            return ResponseEntity.badRequest().build();
        }

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setActivity(activity);
        reservation.setReservationTime(request.reservationTime());

        Reservation savedReservation = reservationRepository.save(reservation);
        return ResponseEntity.ok(savedReservation);
    }


    @GetMapping("/users/{userId}/reservations")
    public ResponseEntity<List<Reservation>> getReservationsByUser(@PathVariable int userId) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reservationRepository.findByUserId(userId));
    }


    @GetMapping("/activities/{activityId}/reservations")
    public ResponseEntity<List<Reservation>> getReservationsByActivity(@PathVariable int activityId) {
        if (!activityRepository.existsById(activityId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reservationRepository.findByActivityId(activityId));
    }
}