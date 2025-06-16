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


    public static record ReservationResponse(
            int id,
            LocalDateTime reservationTime,
            int userId,
            String userFirstName,
            String userLastName,
            int activityId,
            String activityTitle,
            String locationName
    ) {}

    private ReservationResponse toReservationResponse(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getReservationTime(),
                reservation.getUser().getId(),
                reservation.getUser().getFirstName(),
                reservation.getUser().getLastName(),
                reservation.getActivity().getId(),
                reservation.getActivity().getTitle(),
                reservation.getActivity().getLocation().getName()
        );
    }


    public static record ReservationRequest(int userId, int activityId, LocalDateTime reservationTime) {}
    public static record ReservationUpdateRequest(LocalDateTime reservationTime) {}



    @GetMapping("/reservations")
    public List<ReservationResponse> getAllReservations() {
        return reservationRepository.findAll()
                .stream()
                .map(this::toReservationResponse)
                .collect(Collectors.toList());
    }

    @PostMapping("/reservations")
    public ResponseEntity<ReservationResponse> createReservation(@RequestBody ReservationRequest request) {
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
        return ResponseEntity.ok(toReservationResponse(savedReservation));
    }

    @PutMapping("/reservations/{id}")
    public ResponseEntity<ReservationResponse> updateReservation(@PathVariable int id, @RequestBody ReservationUpdateRequest request) {
        return reservationRepository.findById(id)
                .map(reservation -> {
                    reservation.setReservationTime(request.reservationTime());
                    Reservation updatedReservation = reservationRepository.save(reservation);
                    return ResponseEntity.ok(toReservationResponse(updatedReservation));
                }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/users/{userId}/reservations")
    public ResponseEntity<List<ReservationResponse>> getReservationsByUser(@PathVariable int userId) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.notFound().build();
        }
        List<ReservationResponse> responseList = reservationRepository.findByUserId(userId)
                .stream()
                .map(this::toReservationResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/activities/{activityId}/reservations")
    public ResponseEntity<List<ReservationResponse>> getReservationsByActivity(@PathVariable int activityId) {
        if (!activityRepository.existsById(activityId)) {
            return ResponseEntity.notFound().build();
        }
        List<ReservationResponse> responseList = reservationRepository.findByActivityId(activityId)
                .stream()
                .map(this::toReservationResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable int id) {
        return reservationRepository.findById(id)
                .map(reservation -> {
                    reservationRepository.delete(reservation);
                    return ResponseEntity.ok().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}