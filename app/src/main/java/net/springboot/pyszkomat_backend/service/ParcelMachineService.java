package net.springboot.pyszkomat_backend.service;

import jakarta.validation.Valid;
import net.springboot.pyszkomat_backend.exception.ResourceNotFoundException;
import net.springboot.pyszkomat_backend.model.ParcelMachine;
import net.springboot.pyszkomat_backend.repository.ParcelMachineRepository;

public class ParcelMachineService {

    private final ParcelMachineRepository parcelMachineRepository;

    public ParcelMachineService(ParcelMachineRepository parcelMachineRepository) {
        this.parcelMachineRepository = parcelMachineRepository;
    }

    public ParcelMachine getParcelMachine(String id) {
        return parcelMachineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ParcelMachine with id : " + id + " not found"));
    }

    public Iterable<ParcelMachine> getParcelMachines() {
        return parcelMachineRepository.findAll();
    }

    public ParcelMachine addParcelMachine(@Valid ParcelMachine parcelMachine) {
        return parcelMachineRepository.save(parcelMachine);
    }

    public ParcelMachine updateParcelMachine(String id, @Valid ParcelMachine parcelMachine) {
        ParcelMachine _ = this.getParcelMachine(id);

        parcelMachine.id = id;
        return parcelMachineRepository.save(parcelMachine);
    }

    public void deleteParcelMachine(String id) {
        parcelMachineRepository.deleteById(id);
    }
}
