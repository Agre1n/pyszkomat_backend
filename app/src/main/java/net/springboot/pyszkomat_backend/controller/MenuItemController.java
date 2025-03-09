package net.springboot.pyszkomat_backend.controller;

import net.springboot.pyszkomat_backend.dto.crud.MenuItemCrudDto;
import net.springboot.pyszkomat_backend.model.MenuItem;
import net.springboot.pyszkomat_backend.service.MenuItemService;
import net.springboot.pyszkomat_backend.service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/menu_items")
public class MenuItemController {

    private final MenuItemService menuItemService;
    private final RestaurantService restaurantService;

    public MenuItemController(MenuItemService menuItemService, RestaurantService restaurantService) {
        this.menuItemService = menuItemService;
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public ResponseEntity<List<MenuItemCrudDto>> getAllMenuItems() {
        List<MenuItemCrudDto> menuItems = new ArrayList<>();

        for (MenuItem menuItem : menuItemService.getMenuItems()) {
            menuItems.add(new MenuItemCrudDto(menuItem));
        }

        return ResponseEntity.ok(menuItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItemCrudDto> getMenuItemById(@PathVariable Long id) {
        return ResponseEntity.ok(new MenuItemCrudDto(menuItemService.getMenuItem(id)));
    }

    @PostMapping
    public ResponseEntity<MenuItemCrudDto> createMenuItem(@RequestBody MenuItemCrudDto menuItemDTO) {
        MenuItem newMenuItem = menuItemService.addMenuItem(
                menuItemDTO.toMenuItem(restaurantService)
        );

        return ResponseEntity.ok(new MenuItemCrudDto(newMenuItem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItemCrudDto> updateMenuItem(
            @PathVariable Long id,
            @RequestBody MenuItemCrudDto menuItemDTO
    ) {
        MenuItem updatedMenuItem = menuItemService.updateMenuItem(
                id, menuItemDTO.toMenuItem(restaurantService)
        );

        return ResponseEntity.ok(new MenuItemCrudDto(updatedMenuItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
