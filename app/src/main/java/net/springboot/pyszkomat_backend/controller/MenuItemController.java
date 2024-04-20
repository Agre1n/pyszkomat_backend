package net.springboot.pyszkomat_backend.controller;

import jakarta.validation.Valid;
import net.springboot.pyszkomat_backend.exception.ResourceNotFoundException;
import net.springboot.pyszkomat_backend.model.MenuItem;
import net.springboot.pyszkomat_backend.repository.MenuItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/menu_items")
public class MenuItemController {

    private final MenuItemRepository menuItemRepository;

    public MenuItemController(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @GetMapping
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MenuItem with id :" + id + " not found"));

        return ResponseEntity.ok(menuItem);
    }

    @PostMapping
    public MenuItem createMenuItem(@Valid @RequestBody MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(
            @PathVariable Long id,
            @Valid @RequestBody MenuItem menuItemDetails
    ) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MenuItem with id :" + id + " not found"));

        menuItem.setName(menuItemDetails.getName());
        menuItem.setDescription(menuItemDetails.getDescription());
        menuItem.setCategory(menuItemDetails.getCategory());
        menuItem.setPrice(menuItemDetails.getPrice());
        menuItem.setPhotoUrl(menuItemDetails.getPhotoUrl());
        menuItem.setRestaurant(menuItemDetails.getRestaurant());

        MenuItem updatedMenuItem = menuItemRepository.save(menuItem);
        return ResponseEntity.ok(updatedMenuItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteMenuItem(@PathVariable Long id) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MenuItem with id :" + id + " not found"));

        menuItemRepository.delete(menuItem);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
