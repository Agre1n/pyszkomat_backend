package net.springboot.pyszkomat_backend.service;

import jakarta.validation.Valid;
import net.springboot.pyszkomat_backend.exception.ResourceNotFoundException;
import net.springboot.pyszkomat_backend.model.MenuItem;
import net.springboot.pyszkomat_backend.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public MenuItem getMenuItem(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MenuItem with id : " + id + " not found"));
    }

    public Iterable<MenuItem> getMenuItems() {
        return menuItemRepository.findAll();
    }

    public MenuItem addMenuItem(@Valid MenuItem menuItem) {
        menuItem.id = null;
        return menuItemRepository.save(menuItem);
    }

    public MenuItem updateMenuItem(Long id, @Valid MenuItem menuItem) {
        MenuItem _ = getMenuItem(id);

        menuItem.id = id;
        return menuItemRepository.save(menuItem);
    }

    public void deleteMenuItem(Long id) {
        MenuItem _ = getMenuItem(id);

        menuItemRepository.deleteById(id);
    }

    public boolean isEmpty() {
        return menuItemRepository.count() == 0;
    }
}
