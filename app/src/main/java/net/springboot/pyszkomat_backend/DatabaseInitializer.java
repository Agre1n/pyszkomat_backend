package net.springboot.pyszkomat_backend;

import net.springboot.pyszkomat_backend.enumeration.MenuItemCategory;
import net.springboot.pyszkomat_backend.enumeration.OrderStatus;
import net.springboot.pyszkomat_backend.enumeration.RestaurantCategory;
import net.springboot.pyszkomat_backend.model.*;
import net.springboot.pyszkomat_backend.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final CustomerService customerService;
    private final LockerService lockerService;
    private final MenuItemService menuItemService;
    private final OrderItemService orderItemService;
    private final OrderService orderService;
    private final ParcelMachineService parcelMachineService;
    private final RestaurantService restaurantService;

    public DatabaseInitializer(
            CustomerService customerService,
            LockerService lockerService,
            MenuItemService menuItemService,
            OrderItemService orderItemService,
            OrderService orderService,
            ParcelMachineService parcelMachineService,
            RestaurantService restaurantService
    ) {
        this.customerService = customerService;
        this.lockerService = lockerService;
        this.menuItemService = menuItemService;
        this.orderItemService = orderItemService;
        this.orderService = orderService;
        this.parcelMachineService = parcelMachineService;
        this.restaurantService = restaurantService;
    }

    @Override
    public void run(String... args) {
        initializeData();
    }

    private void initializeData() {
        if (!customerService.isEmpty()) return;
        if (!lockerService.isEmpty()) return;
        if (!menuItemService.isEmpty()) return;
        if (!orderItemService.isEmpty()) return;
        if (!orderService.isEmpty()) return;
        if (!parcelMachineService.isEmpty()) return;
        if (!restaurantService.isEmpty()) return;

        Restaurant restaurant1 = new Restaurant(
                null,
                "Spaghetti house",
                "ul. Świdnicka 35, 50-073 Wrocław",
                "713-433-434",
                "Authentic Italian cuisine",
                RestaurantCategory.ITALIAN,
                4,
                "https://example.com/spaghetti_house.jpg",
                null,
                null
        );
        Restaurant restaurant2 = new Restaurant(
                null,
                "Burger Haven",
                "Plac Solny 16, 50-062 Wrocław",
                "456-789-012",
                "Home of the juiciest burgers in town",
                RestaurantCategory.FAST_FOOD,
                5,
                "https://example.com/burger_haven.jpg",
                null,
                null
        );
        restaurantService.addRestaurant(restaurant1);
        restaurantService.addRestaurant(restaurant2);

        Customer customer1 = new Customer(
                null,
                "Anna",
                "Kowalska",
                "anna.kowalska@example.com",
                "555-123-456",
                "Rynek 23, 50-101 Wrocław",
                null
        );
        Customer customer2 = new Customer(
                null,
                "Jan",
                "Nowak",
                "jan.nowak@example.com",
                "321-654-987",
                "ul. Kazimierza Wielkiego 54, 50-077 Wrocław",
                null
        );
        customerService.addCustomer(customer1);
        customerService.addCustomer(customer2);

        MenuItem menuItem1 = new MenuItem(
                null,
                "Spaghetti Bolognese",
                "Classic pasta dish with meat sauce",
                MenuItemCategory.DINNER,
                12.99f,
                "https://example.com/spaghetti_bolognese.jpg",
                restaurant1
        );
        MenuItem menuItem2 = new MenuItem(
                null,
                "Cheeseburger",
                "Juicy beef patty with melted cheese",
                MenuItemCategory.DINNER,
                8.99f,
                "https://example.com/cheeseburger.jpg",
                restaurant2
        );
        MenuItem menuItem3 = new MenuItem(
                null,
                "Tiramisu",
                "Classic Italian dessert made with coffee-soaked ladyfingers",
                MenuItemCategory.DESSERT,
                6.99f,
                "https://example.com/tiramisu.jpg",
                restaurant1
        );
        menuItemService.addMenuItem(menuItem1);
        menuItemService.addMenuItem(menuItem2);
        menuItemService.addMenuItem(menuItem3);

        ParcelMachine parcelMachine1 = new ParcelMachine(
                "WRO-001M",
                17.030321,
                51.109188,
                "Plac Dominikański 3, 50-159 Wrocław",
                null,
                List.of(restaurant1, restaurant2)
        );
        ParcelMachine parcelMachine2 = new ParcelMachine(
                "WRO-123E",
                17.029784,
                51.108118,
                "ul. Kazimierza Wielkiego 17, 50-077 Wrocław",
                null,
                List.of(restaurant2)
        );
        parcelMachineService.addParcelMachine(parcelMachine1);
        parcelMachineService.addParcelMachine(parcelMachine2);

        Locker locker1 = new Locker(null, true, parcelMachine1);
        Locker locker2 = new Locker(null, false, parcelMachine1);
        Locker locker3 = new Locker(null, false, parcelMachine2);
        Locker locker4 = new Locker(null, true, parcelMachine2);
        lockerService.addLocker(locker1);
        lockerService.addLocker(locker2);
        lockerService.addLocker(locker3);
        lockerService.addLocker(locker4);

        Order order1 = new Order(
                null,
                LocalDateTime.now(),
                null,
                null,
                OrderStatus.PREPARED,
                false,
                customer1,
                locker2,
                null
        );
        Order order2 = new Order(
                null,
                LocalDateTime.now().minusHours(2),
                LocalDateTime.now(),
                null,
                OrderStatus.READY_FOR_PICKUP,
                true,
                customer2,
                locker3,
                null
        );
        orderService.addOrder(order1);
        orderService.addOrder(order2);

        OrderItem orderItem1 = new OrderItem(null, 2, menuItem1, order1);
        OrderItem orderItem2 = new OrderItem(null, 1, menuItem3, order1);
        OrderItem orderItem3 = new OrderItem(null, 1, menuItem2, order2);
        orderItemService.addOrderItem(orderItem1);
        orderItemService.addOrderItem(orderItem2);
        orderItemService.addOrderItem(orderItem3);
    }
}
