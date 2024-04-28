package net.springboot.pyszkomat_backend;

import net.springboot.pyszkomat_backend.enumeration.MenuItemCategory;
import net.springboot.pyszkomat_backend.enumeration.OrderStatus;
import net.springboot.pyszkomat_backend.enumeration.RestaurantCategory;
import net.springboot.pyszkomat_backend.model.*;
import net.springboot.pyszkomat_backend.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final LockerRepository lockerRepository;
    private final MenuItemRepository menuItemRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ParcelMachineRepository parcelMachineRepository;
    private final RestaurantRepository restaurantRepository;

    public DatabaseInitializer(
            CustomerRepository customerRepository,
            LockerRepository lockerRepository,
            MenuItemRepository menuItemRepository,
            OrderItemRepository orderItemRepository,
            OrderRepository orderRepository,
            ParcelMachineRepository parcelMachineRepository,
            RestaurantRepository restaurantRepository
    ) {
        this.customerRepository = customerRepository;
        this.lockerRepository = lockerRepository;
        this.menuItemRepository = menuItemRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.parcelMachineRepository = parcelMachineRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void run(String... args) {
        initializeData();
    }

    private void initializeData() {
        if (customerRepository.count() > 0) {
            return;
        }
        if (lockerRepository.count() > 0) {
            return;
        }
        if (menuItemRepository.count() > 0) {
            return;
        }
        if (orderItemRepository.count() > 0) {
            return;
        }
        if (orderRepository.count() > 0) {
            return;
        }
        if (parcelMachineRepository.count() > 0) {
            return;
        }
        if (restaurantRepository.count() > 0) {
            return;
        }

        Restaurant restaurant1 = new Restaurant("Spaghetti House", "123 Main St", "555-1234", "Authentic Italian cuisine", RestaurantCategory.ITALIAN, 4, "https://example.com/spaghetti_house.jpg", new ArrayList<>(), new ArrayList<>());
        Restaurant restaurant2 = new Restaurant("Burger Haven", "456 Oak St", "555-5678", "Home of the juiciest burgers in town", RestaurantCategory.FAST_FOOD, 4, "https://example.com/burger_haven.jpg", new ArrayList<>(), new ArrayList<>());
        restaurantRepository.saveAll(List.of(restaurant1, restaurant2));

        MenuItem menuItem1 = new MenuItem("Spaghetti Bolognese", "Classic pasta dish with meat sauce", MenuItemCategory.DINNER, 12.99f, "https://example.com/spaghetti_bolognese.jpg", restaurant1);
        MenuItem menuItem2 = new MenuItem("Cheeseburger", "Juicy beef patty with melted cheese", MenuItemCategory.DINNER, 8.99f, "https://example.com/cheeseburger.jpg", restaurant2);
        MenuItem menuItem3 = new MenuItem("Tiramisu", "Classic Italian dessert made with coffee-soaked ladyfingers", MenuItemCategory.DESSERT, 6.99f, "https://example.com/tiramisu.jpg", restaurant1);
        menuItemRepository.saveAll(List.of(menuItem1, menuItem2, menuItem3));

        Customer customer1 = new Customer("Alice", "Smith", "alice@example.com", "555-1111", "789 Elm St", new ArrayList<>());
        Customer customer2 = new Customer("Bob", "Jones", "bob@example.com", "555-2222", "456 Maple Ave", new ArrayList<>());
        customerRepository.saveAll(List.of(customer1, customer2));

        Locker locker1 = new Locker(true, null);
        Locker locker2 = new Locker(false, null);
        lockerRepository.saveAll(List.of(locker1, locker2));

        ParcelMachine parcelMachine1 = new ParcelMachine("PM1", 40.7128, -74.0060, "123 Elm St", new ArrayList<>(), new ArrayList<>());
        ParcelMachine parcelMachine2 = new ParcelMachine("PM2", 34.0522, -118.2437, "456 Maple Ave", new ArrayList<>(), new ArrayList<>());
        parcelMachineRepository.saveAll(List.of(parcelMachine1, parcelMachine2));

        Order order1 = new Order(LocalDateTime.now(), null, null, OrderStatus.PREPARED, false, customer1, locker1, new ArrayList<>());
        Order order2 = new Order(LocalDateTime.now(), null, null, OrderStatus.PREPARED, false, customer2, locker2, new ArrayList<>());
        orderRepository.saveAll(List.of(order1, order2));

        OrderItem orderItem1 = new OrderItem(1, menuItem1, order1);
        OrderItem orderItem2 = new OrderItem(2, menuItem2, order1);
        OrderItem orderItem3 = new OrderItem(1, menuItem3, order2);
        orderItemRepository.saveAll(List.of(orderItem1, orderItem2, orderItem3));
    }
}
