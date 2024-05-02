package net.springboot.pyszkomat_backend.service;

import jakarta.validation.Valid;
import net.springboot.pyszkomat_backend.exception.ResourceNotFoundException;
import net.springboot.pyszkomat_backend.model.Locker;
import net.springboot.pyszkomat_backend.repository.LockerRepository;

public class LockerService {

    private final LockerRepository lockerRepository;

    public LockerService(LockerRepository lockerRepository) {
        this.lockerRepository = lockerRepository;
    }

    public Locker getLocker(Long id) {
        return lockerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Locker with id : " + id + " not found"));
    }

    public Iterable<Locker> getLockers() {
        return lockerRepository.findAll();
    }

    public Locker addLocker(@Valid Locker locker) {
        locker.id = null;
        return lockerRepository.save(locker);
    }

    public Locker updateLocker(Long id, @Valid Locker locker) {
        Locker _ = this.getLocker(id);

        locker.id = id;
        return lockerRepository.save(locker);
    }

    public void deleteLocker(Long id) {
        lockerRepository.deleteById(id);
    }
}
