package sk.kasv.ferencak.SportReservation.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.kasv.ferencak.SportReservation.Entity.Activity;
import sk.kasv.ferencak.SportReservation.Repository.ActivityRepository;
import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;

    @PostMapping
    public Activity createActivity(@RequestBody Activity activity) {
        return activityRepository.save(activity);
    }

    @GetMapping
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }
}