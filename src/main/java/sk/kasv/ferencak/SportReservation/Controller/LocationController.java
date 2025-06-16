package sk.kasv.ferencak.SportReservation.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.kasv.ferencak.SportReservation.Entity.Location;
import sk.kasv.ferencak.SportReservation.Repository.LocationRepository;
import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    public static record LocationRequest(String name, String address) {}


    @PostMapping
    public Location createLocation(@RequestBody LocationRequest request) {
        Location newLocation = new Location(request.name(), request.address());
        return locationRepository.save(newLocation);
    }

    @GetMapping
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable int id) {
        return locationRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable int id, @RequestBody LocationRequest request) {
        // Nájdi existujúce miesto podľa ID
        return locationRepository.findById(id)
                .map(location -> {
                    location.setName(request.name());
                    location.setAddress(request.address());
                    Location updatedLocation = locationRepository.save(location);
                    return ResponseEntity.ok(updatedLocation);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable int id) {
        return locationRepository.findById(id)
                .map(location -> {
                    locationRepository.delete(location);
                    return ResponseEntity.ok().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}