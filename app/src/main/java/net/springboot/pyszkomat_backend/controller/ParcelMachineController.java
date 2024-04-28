package net.springboot.pyszkomat_backend.controller;

import jakarta.validation.Valid;
import net.springboot.pyszkomat_backend.exception.ResourceNotFoundException;
import net.springboot.pyszkomat_backend.model.ParcelMachine;
import net.springboot.pyszkomat_backend.repository.ParcelMachineRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/parcel_machines")
public class ParcelMachineController {

    private final ParcelMachineRepository parcelMachineRepository;

    public ParcelMachineController(ParcelMachineRepository parcelMachineRepository) {
        this.parcelMachineRepository = parcelMachineRepository;
    }

    @GetMapping
    public List<ParcelMachine> getAllParcelMachines() {
        return parcelMachineRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParcelMachine> getParcelMachineById(@PathVariable Long id) {
        ParcelMachine parcelMachine = parcelMachineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ParcelMachine with id :" + id + " not found"));

        return ResponseEntity.ok(parcelMachine);
    }

    @PostMapping
    public ParcelMachine createParcelMachine(@Valid @RequestBody ParcelMachine parcelMachine) {
        return parcelMachineRepository.save(parcelMachine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParcelMachine> updateParcelMachine(
            @PathVariable Long id,
            @Valid @RequestBody ParcelMachine parcelMachineDetails
    ) {
        ParcelMachine parcelMachine = parcelMachineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ParcelMachine with id :" + id + " not found"));

        parcelMachine.setLatitude(parcelMachineDetails.getLatitude());
        parcelMachine.setLongitude(parcelMachineDetails.getLongitude());
        parcelMachine.setAddress(parcelMachineDetails.getAddress());
        parcelMachine.setLockers(parcelMachineDetails.getLockers());
        parcelMachine.setRestaurants(parcelMachineDetails.getRestaurants());

        ParcelMachine updatedParcelMachine = parcelMachineRepository.save(parcelMachine);
        return ResponseEntity.ok(updatedParcelMachine);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteParcelMachine(@PathVariable Long id) {
        ParcelMachine parcelMachine = parcelMachineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ParcelMachine with id :" + id + " not found"));

        parcelMachineRepository.delete(parcelMachine);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
