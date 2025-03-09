package net.springboot.pyszkomat_backend.controller;

import net.springboot.pyszkomat_backend.dto.crud.RestaurantCrudDto;
import net.springboot.pyszkomat_backend.model.Restaurant;
import net.springboot.pyszkomat_backend.service.MenuItemService;
import net.springboot.pyszkomat_backend.service.ParcelMachineService;
import net.springboot.pyszkomat_backend.service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final MenuItemService menuItemService;
    private final RestaurantService restaurantService;
    private final ParcelMachineService parcelMachineService;

    public RestaurantController(
            MenuItemService menuItemService,
            RestaurantService restaurantService,
            ParcelMachineService parcelMachineService
    ) {
        this.menuItemService = menuItemService;
        this.restaurantService = restaurantService;
        this.parcelMachineService = parcelMachineService;
    }

    @GetMapping
    public ResponseEntity<List<RestaurantCrudDto>> getAllRestaurants() {
        List<RestaurantCrudDto> restaurants = new ArrayList<>();

        for (Restaurant restaurant : restaurantService.getRestaurants()) {
            restaurants.add(new RestaurantCrudDto(restaurant));
        }

        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantCrudDto> getRestaurantById(@PathVariable Long id) {
        return ResponseEntity.ok(new RestaurantCrudDto(restaurantService.getRestaurant(id)));
    }

    @PostMapping
    public ResponseEntity<RestaurantCrudDto> addRestaurant(@RequestBody RestaurantCrudDto restaurantCrudDto) {
        restaurantCrudDto.menuItemsIds = new ArrayList<>();
        restaurantCrudDto.parcelMachinesIds = new ArrayList<>();

        Restaurant newRestaurant = restaurantService.addRestaurant(
                restaurantCrudDto.toRestaurant(menuItemService, parcelMachineService)
        );

        return ResponseEntity.ok(new RestaurantCrudDto(newRestaurant));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantCrudDto> updateRestaurant(@PathVariable Long id, @RequestBody RestaurantCrudDto restaurantCrudDto) {
        restaurantCrudDto.menuItemsIds = new ArrayList<>();
        restaurantCrudDto.parcelMachinesIds = new ArrayList<>();

        Restaurant updatedRestaurant = restaurantService.updateRestaurant(
                id, restaurantCrudDto.toRestaurant(menuItemService, parcelMachineService)
        );

        return ResponseEntity.ok(new RestaurantCrudDto(updatedRestaurant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
