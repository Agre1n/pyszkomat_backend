package net.springboot.pyszkomat_backend.controller;

import net.springboot.pyszkomat_backend.dto.crud.ParcelMachineCrudDto;
import net.springboot.pyszkomat_backend.model.ParcelMachine;
import net.springboot.pyszkomat_backend.service.LockerService;
import net.springboot.pyszkomat_backend.service.ParcelMachineService;
import net.springboot.pyszkomat_backend.service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/parcel_machines")
public class ParcelMachineController {

    private final LockerService lockerService;
    private final ParcelMachineService parcelMachineService;
    private final RestaurantService restaurantService;

    public ParcelMachineController(
            LockerService lockerService,
            ParcelMachineService parcelMachineService,
            RestaurantService restaurantService
    ) {
        this.lockerService = lockerService;
        this.parcelMachineService = parcelMachineService;
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public ResponseEntity<List<ParcelMachineCrudDto>> getAllParcelMachines() {
        List<ParcelMachineCrudDto> parcelMachines = new ArrayList<>();

        for (ParcelMachine parcelMachine : parcelMachineService.getParcelMachines()) {
            parcelMachines.add(new ParcelMachineCrudDto(parcelMachine));
        }

        return ResponseEntity.ok(parcelMachines);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParcelMachineCrudDto> getParcelMachineById(@PathVariable String id) {
        return ResponseEntity.ok(new ParcelMachineCrudDto(parcelMachineService.getParcelMachine(id)));
    }

    @PostMapping
    public ResponseEntity<ParcelMachineCrudDto> addParcelMachine(@RequestBody ParcelMachineCrudDto parcelMachineCrudDto) {
        parcelMachineCrudDto.lockersIds = new ArrayList<>();

        ParcelMachine newParcelMachine = parcelMachineService.addParcelMachine(
                parcelMachineCrudDto.toParcelMachine(lockerService, restaurantService)
        );

        return ResponseEntity.ok(new ParcelMachineCrudDto(newParcelMachine));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParcelMachineCrudDto> updateParcelMachine(
            @PathVariable String id,
            @RequestBody ParcelMachineCrudDto parcelMachineCrudDto
    ) {
        parcelMachineCrudDto.lockersIds = new ArrayList<>();

        ParcelMachine updatedParcelMachine = parcelMachineService.updateParcelMachine(
                id, parcelMachineCrudDto.toParcelMachine(lockerService, restaurantService)
        );

        return ResponseEntity.ok(new ParcelMachineCrudDto(updatedParcelMachine));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteParcelMachine(@PathVariable String id) {
        parcelMachineService.deleteParcelMachine(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
