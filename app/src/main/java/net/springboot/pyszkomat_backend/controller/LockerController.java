package net.springboot.pyszkomat_backend.controller;

import jakarta.validation.Valid;
import net.springboot.pyszkomat_backend.exception.ResourceNotFoundException;
import net.springboot.pyszkomat_backend.model.Locker;
import net.springboot.pyszkomat_backend.repository.LockerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/lockers")
public class LockerController {

    private final LockerRepository lockerRepository;

    public LockerController(LockerRepository lockerRepository) {
        this.lockerRepository = lockerRepository;
    }

    @GetMapping
    public List<Locker> getAllLockers() {
        return lockerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Locker> getLockerById(@PathVariable Long id) {
        Locker locker = lockerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Locker with id :" + id + " not found"));

        return ResponseEntity.ok(locker);
    }

    @PostMapping
    public Locker createLocker(@Valid @RequestBody Locker locker) {
        return lockerRepository.save(locker);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Locker> updateLocker(
            @PathVariable Long id,
            @Valid @RequestBody Locker lockerDetails
    ) {
        Locker locker = lockerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Locker with id :" + id + " not found"));

        locker.setIsEmpty(lockerDetails.getIsEmpty());
        locker.setParcelMachine(lockerDetails.getParcelMachine());

        Locker updatedLocker = lockerRepository.save(locker);
        return ResponseEntity.ok(updatedLocker);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteLocker(@PathVariable Long id) {
        Locker locker = lockerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Locker with id :" + id + " not found"));

        lockerRepository.delete(locker);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
