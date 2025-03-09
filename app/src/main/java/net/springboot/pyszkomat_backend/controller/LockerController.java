package net.springboot.pyszkomat_backend.controller;

import net.springboot.pyszkomat_backend.dto.crud.LockerCrudDto;
import net.springboot.pyszkomat_backend.model.Locker;
import net.springboot.pyszkomat_backend.service.LockerService;
import net.springboot.pyszkomat_backend.service.ParcelMachineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/lockers")
public class LockerController {

    private final LockerService lockerService;
    private final ParcelMachineService parcelMachineService;

    public LockerController(LockerService lockerService, ParcelMachineService parcelMachineService) {
        this.lockerService = lockerService;
        this.parcelMachineService = parcelMachineService;
    }

    @GetMapping
    public ResponseEntity<List<LockerCrudDto>> getAllLockers() {
        List<LockerCrudDto> lockers = new ArrayList<>();

        for (Locker locker : lockerService.getLockers()) {
            lockers.add(new LockerCrudDto(locker));
        }

        return ResponseEntity.ok(lockers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LockerCrudDto> getLockerById(@PathVariable Long id) {
        return ResponseEntity.ok(new LockerCrudDto(lockerService.getLocker(id)));
    }

    @PostMapping
    public ResponseEntity<LockerCrudDto> createLocker(@RequestBody LockerCrudDto lockerDTO) {
        Locker newLocker = lockerService.addLocker(
                lockerDTO.toLocker(parcelMachineService)
        );

        return ResponseEntity.ok(new LockerCrudDto(newLocker));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LockerCrudDto> updateLocker(
            @PathVariable Long id,
            @RequestBody LockerCrudDto lockerDTO
    ) {
        Locker updatedLocker = lockerService.updateLocker(
                id, lockerDTO.toLocker(parcelMachineService)
        );

        return ResponseEntity.ok(new LockerCrudDto(updatedLocker));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteLocker(@PathVariable Long id) {
        lockerService.deleteLocker(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
