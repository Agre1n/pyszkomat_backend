package net.springboot.pyszkomat_backend.dto.crud;

import net.springboot.pyszkomat_backend.model.Customer;
import net.springboot.pyszkomat_backend.model.Order;
import net.springboot.pyszkomat_backend.model.ParcelMachine;
import net.springboot.pyszkomat_backend.model.Restaurant;
import net.springboot.pyszkomat_backend.service.OrderService;
import net.springboot.pyszkomat_backend.service.ParcelMachineService;
import net.springboot.pyszkomat_backend.service.RestaurantService;

import java.util.ArrayList;
import java.util.List;

public class CustomerCrudDto {

    public Long id;
    public String firstName;
    public String lastName;
    public String email;
    public String phoneNumber;
    public String address;
    public List<Long> ordersIds;
    public List<Long> favoriteRestaurantsIds;
    public List<String> favoriteParcelMachinesIds;

    public CustomerCrudDto() {
    }

    public CustomerCrudDto(Customer customer) {
        id = customer.id;
        firstName = customer.firstName;
        lastName = customer.lastName;
        email = customer.email;
        phoneNumber = customer.phoneNumber;
        address = customer.address;

        ordersIds = new ArrayList<>();
        for (Order order : customer.orders) {
            ordersIds.add(order.id);
        }

        favoriteRestaurantsIds = new ArrayList<>();
        for (Restaurant restaurant : customer.favoriteRestaurants) {
            favoriteRestaurantsIds.add(restaurant.id);
        }

        favoriteParcelMachinesIds = new ArrayList<>();
        for (ParcelMachine parcelMachine : customer.favoriteParcelMachines) {
            favoriteParcelMachinesIds.add(parcelMachine.id);
        }
    }

    public Customer toCustomer(OrderService orderService, RestaurantService restaurantService, ParcelMachineService parcelMachineService) {
        List<Order> orders = new ArrayList<>();
        for (Long id : ordersIds) {
            orders.add(orderService.getOrder(id));
        }

        List<Restaurant> restaurants = new ArrayList<>();
        for (Long restaurantId : favoriteRestaurantsIds) {
            restaurants.add(restaurantService.getRestaurant(restaurantId));
        }

        List<ParcelMachine> parcelMachines = new ArrayList<>();
        for (String parcelMachineId : favoriteParcelMachinesIds) {
            parcelMachines.add(parcelMachineService.getParcelMachine(parcelMachineId));
        }

        return new Customer(
                id,
                firstName,
                lastName,
                email,
                phoneNumber,
                address,
                orders,
                restaurants,
                parcelMachines
        );
    }
}
