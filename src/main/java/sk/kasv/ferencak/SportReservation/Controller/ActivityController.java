package sk.kasv.ferencak.SportReservation.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.kasv.ferencak.SportReservation.Entity.Activity;
import sk.kasv.ferencak.SportReservation.Entity.Location;
import sk.kasv.ferencak.SportReservation.Repository.ActivityRepository;
import sk.kasv.ferencak.SportReservation.Repository.LocationRepository;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private LocationRepository locationRepository;

    public static record ActivityRequest(String title, int locationId) {}


    @PostMapping
    public ResponseEntity<Activity> createActivity(@RequestBody ActivityRequest request) {
        Location location = locationRepository.findById(request.locationId()).orElse(null);

        if (location == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Activity activity = new Activity(request.title(), location);
        Activity savedActivity = activityRepository.save(activity);
        return ResponseEntity.ok(savedActivity);
    }

    @GetMapping
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable int id) {
        return activityRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable int id, @RequestBody ActivityRequest request) {
        return activityRepository.findById(id)
                .map(activity -> {
                    Location location = locationRepository.findById(request.locationId()).orElse(null);
                    if (location == null) {
                        return ResponseEntity.badRequest().<Activity>body(null);
                    }
                    activity.setTitle(request.title());
                    activity.setLocation(location);
                    Activity updatedActivity = activityRepository.save(activity);
                    return ResponseEntity.ok(updatedActivity);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable int id) {
        return activityRepository.findById(id)
                .map(activity -> {
                    activityRepository.delete(activity);
                    return ResponseEntity.ok().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}