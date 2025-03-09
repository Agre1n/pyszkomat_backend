package net.springboot.pyszkomat_backend;

import net.springboot.pyszkomat_backend.enumeration.MenuItemCategory;
import net.springboot.pyszkomat_backend.enumeration.OrderStatus;
import net.springboot.pyszkomat_backend.enumeration.RestaurantCategory;
import net.springboot.pyszkomat_backend.model.*;
import net.springboot.pyszkomat_backend.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
                "Spaghetti House",
                "ul. Świdnicka 35, 50-073 Wrocław",
                "713-433-434",
                "Autentyczna kuchnia włoska",
                RestaurantCategory.ITALIAN,
                4,
                "a1420224-6998-4595-a70b-ef89d8a20012_spaghetti_house.jpg",
                new ArrayList<>(),
                new ArrayList<>()
        );
        Restaurant restaurant2 = new Restaurant(
                null,
                "Burger Haven",
                "Plac Solny 16, 50-062 Wrocław",
                "456-789-012",
                "Najlepsze burgery w mieście",
                RestaurantCategory.FAST_FOOD,
                5,
                "b6b16909-4199-465a-b5db-f768d8a1beee_burger_haven.jpg",
                new ArrayList<>(),
                new ArrayList<>()
        );
        Restaurant restaurant3 = new Restaurant(
                null,
                "Tokyo Sushi",
                "ul. Krawiecka 10, 50-148 Wrocław",
                "789-456-123",
                "Świeże sushi i sashimi",
                RestaurantCategory.ASIAN,
                5,
                "ca204006-97f9-4fc0-b93f-c478c82a914e_tokyo_sushi.jpg",
                new ArrayList<>(),
                new ArrayList<>()
        );
        Restaurant restaurant4 = new Restaurant(
                null,
                "Taco Fiesta",
                "ul. Oławska 27, 50-123 Wrocław",
                "321-654-789",
                "Najlepsze tacos w mieście",
                RestaurantCategory.MEXICAN,
                4,
                "30b9ddf3-1e5b-40c5-bac6-d6db09a8d055_taco_fiesta.jpg",
                new ArrayList<>(),
                new ArrayList<>()
        );
        Restaurant restaurant5 = new Restaurant(
                null,
                "Pizza Palace",
                "ul. Legnicka 58, 54-204 Wrocław",
                "123-456-789",
                "Pyszne pizze z świeżymi składnikami",
                RestaurantCategory.ITALIAN,
                5,
                "f7fe4241-1bdb-4064-9540-b70f343ab31b_pizza_palace.jpg",
                new ArrayList<>(),
                new ArrayList<>()
        );
        Restaurant restaurant6 = new Restaurant(
                null,
                "Cafe Mocha",
                "ul. Piłsudskiego 105, 50-085 Wrocław",
                "654-321-987",
                "Najlepsza kawa w mieście",
                RestaurantCategory.BAKERY,
                5,
                "485f0d16-f493-4f11-82dc-995b89cb2fca_cafe_mocha.jpg",
                new ArrayList<>(),
                new ArrayList<>()
        );
        Restaurant restaurant7 = new Restaurant(
                null,
                "Deserownia",
                "ul. Grunwaldzka 23, 50-355 Wrocław",
                "789-654-123",
                "Pyszne desery i ciasta",
                RestaurantCategory.BAKERY,
                5,
                "4b9cafe6-306a-459b-931b-ddcc87c24c1c_deserownia.jpg",
                new ArrayList<>(),
                new ArrayList<>()
        );
        Restaurant restaurant8 = new Restaurant(
                null,
                "Taj Mahal",
                "ul. Katedralna 5, 50-328 Wrocław",
                "321-987-654",
                "Autentyczna kuchnia indyjska",
                RestaurantCategory.ASIAN,
                4,
                "a8ce10f8-1f77-438e-80b2-0650d150f734_taj_mahal.jpg",
                new ArrayList<>(),
                new ArrayList<>()
        );
        Restaurant restaurant9 = new Restaurant(
                null,
                "Greco",
                "ul. Drobnera 18, 50-257 Wrocław",
                "987-654-321",
                "Najlepsza kuchnia grecka",
                RestaurantCategory.SEAFOOD,
                5,
                "5950a70f-1c67-4434-8662-b8465682a310_greco.jpg",
                new ArrayList<>(),
                new ArrayList<>()
        );
        Restaurant restaurant10 = new Restaurant(
                null,
                "La Vegana",
                "ul. Poniatowskiego 31, 50-325 Wrocław",
                "456-123-789",
                "Zdrowe wegańskie jedzenie",
                RestaurantCategory.VEGAN,
                5,
                "9bca6320-b457-44d1-a9ac-f655394891a3_la_vegana.jpg",
                new ArrayList<>(),
                new ArrayList<>()
        );
        restaurantService.addRestaurant(restaurant1);
        restaurantService.addRestaurant(restaurant2);
        restaurantService.addRestaurant(restaurant3);
        restaurantService.addRestaurant(restaurant4);
        restaurantService.addRestaurant(restaurant5);
        restaurantService.addRestaurant(restaurant6);
        restaurantService.addRestaurant(restaurant7);
        restaurantService.addRestaurant(restaurant8);
        restaurantService.addRestaurant(restaurant9);
        restaurantService.addRestaurant(restaurant10);

        List<MenuItem> menuItems = List.of(
                new MenuItem(
                        null,
                        "Spaghetti Bolognese",
                        "Klasyczne danie makaronowe z sosem mięsnym",
                        MenuItemCategory.DINNER,
                        12.99f,
                        "a658854c-3e20-473e-9a00-cf7794abd2ed_spaghetti_bolognese.jpg",
                        restaurant1
                ),
                new MenuItem(
                        null,
                        "Lasagna",
                        "Warstwowe danie z makaronu, mięsa i sera",
                        MenuItemCategory.DINNER,
                        14.99f,
                        "2bc1e13a-00d5-4ff0-88ac-accc7ec48bfe_lasagna.jpg",
                        restaurant1
                ),
                new MenuItem(
                        null,
                        "Risotto",
                        "Kremowe danie z ryżu z warzywami",
                        MenuItemCategory.DINNER,
                        13.99f,
                        "a4449929-f1f1-4c57-a4de-504b053f366e_risotto.jpg",
                        restaurant1
                ),
                new MenuItem(
                        null,
                        "Tiramisu",
                        "Klasyczny włoski deser z biszkoptami nasączonymi kawą",
                        MenuItemCategory.DESSERT,
                        6.99f,
                        "2e5ed1ea-00ac-4c87-bdf2-6babf8bb7b6f_tiramisu.jpg",
                        restaurant1
                ),
                new MenuItem(
                        null,
                        "Panna Cotta",
                        "Włoski deser z kremem śmietanowym",
                        MenuItemCategory.DESSERT,
                        5.99f,
                        "94076d1d-fd26-4180-ae3d-32aa1fef5720_panna_cotta.jpg",
                        restaurant1
                ),

                new MenuItem(
                        null,
                        "Cheeseburger",
                        "Soczysty burger wołowy z roztopionym serem",
                        MenuItemCategory.DINNER,
                        8.99f,
                        "e2fad4e7-7a2f-41bf-8d9e-836271187fc5_cheeseburger.jpg",
                        restaurant2
                ),
                new MenuItem(
                        null,
                        "Chicken Burger",
                        "Burger z grillowanym kurczakiem",
                        MenuItemCategory.DINNER,
                        9.99f,
                        "8aa7b4f6-adee-41b5-a7c6-f81661a86941_chicken_burger.jpg",
                        restaurant2
                ),
                new MenuItem(
                        null,
                        "Veggie Burger",
                        "Burger warzywny z sosem z awokado",
                        MenuItemCategory.DINNER,
                        7.99f,
                        "0ba3bf2b-928d-419a-825e-5fff6458aefd_veggie_burger.jpg",
                        restaurant2
                ),
                new MenuItem(
                        null,
                        "Frytki",
                        "Złociste frytki z ziemniaków",
                        MenuItemCategory.SNACK,
                        3.99f,
                        "b8cc004b-970c-4a41-8bea-d7276df55a7a_frytki.jpg",
                        restaurant2
                ),
                new MenuItem(
                        null,
                        "Coleslaw",
                        "Świeża surówka z kapusty",
                        MenuItemCategory.SNACK,
                        2.99f,
                        "393efd24-2b5c-4a0d-afe4-d0827e922fa8_coleslaw.jpg",
                        restaurant2
                ),

                new MenuItem(
                        null,
                        "Nigiri",
                        "Tradycyjne nigiri sushi z łososiem",
                        MenuItemCategory.DINNER,
                        10.99f,
                        "353e2bc6-12ab-41e7-8c1b-e73cea7abfd6_nigiri.jpg",
                        restaurant3
                ),
                new MenuItem(
                        null,
                        "Maki",
                        "Rolki sushi z warzywami i rybą",
                        MenuItemCategory.DINNER,
                        11.99f,
                        "7e33bba8-c802-4cd1-8b3f-e4791b4e8bf4_maki.jpg",
                        restaurant3
                ),
                new MenuItem(
                        null,
                        "Sashimi",
                        "Plasterki świeżej ryby",
                        MenuItemCategory.DINNER,
                        12.99f,
                        "e51b3daa-5529-42e5-80f7-c9935f4ed820_sashimi.jpg",
                        restaurant3
                ),
                new MenuItem(
                        null,
                        "Tempura",
                        "Smażone warzywa i owoce morza w cieście",
                        MenuItemCategory.SNACK,
                        9.99f,
                        "0ac447dd-f1a5-4c14-b042-73c8e182f5d4_tempura.jpg",
                        restaurant3
                ),
                new MenuItem(
                        null,
                        "Miso Soup",
                        "Japońska zupa miso z tofu",
                        MenuItemCategory.SOUP,
                        4.99f,
                        "cc3f7025-9711-4672-b344-c685356e6b03_miso_soup.jpg",
                        restaurant3
                ),

                new MenuItem(
                        null,
                        "Tacos Al Pastor",
                        "Tacos z wieprzowiną i ananasem",
                        MenuItemCategory.DINNER,
                        8.99f,
                        "bac2643d-323b-415f-8902-ee1a487b7ea7_quesadilla.jpg",
                        restaurant4
                ),
                new MenuItem(
                        null,
                        "Quesadilla",
                        "Tortilla z serem i kurczakiem",
                        MenuItemCategory.DINNER,
                        9.99f,
                        "bac2643d-323b-415f-8902-ee1a487b7ea7_quesadilla.jpg",
                        restaurant4
                ),
                new MenuItem(
                        null,
                        "Guacamole",
                        "Pasta z awokado z przyprawami",
                        MenuItemCategory.SNACK,
                        5.99f,
                        "fc481b57-8615-4abc-90e2-5a4afdd6a2af_guacamole.jpg",
                        restaurant4
                ),
                new MenuItem(
                        null,
                        "Nachos",
                        "Chrupiące nachos z serem",
                        MenuItemCategory.SNACK,
                        4.99f,
                        "6ec115cf-3eb6-407b-a192-7c6d91451088_nachos.jpg",
                        restaurant4
                ),
                new MenuItem(
                        null,
                        "Churros",
                        "Słodkie ciastka smażone z cukrem",
                        MenuItemCategory.DESSERT,
                        6.99f,
                        "b5d54b06-18e4-4a8b-bcc3-55682f7a1d52_churros.jpg",
                        restaurant4
                ),

                new MenuItem(
                        null,
                        "Margherita",
                        "Pizza z sosem pomidorowym i serem mozzarella",
                        MenuItemCategory.DINNER,
                        9.99f,
                        "a526926d-10bc-4a6d-972f-35d3abd9cbee_margherita.jpg",
                        restaurant5
                ),
                new MenuItem(
                        null,
                        "Pepperoni",
                        "Pizza z plasterkami pepperoni",
                        MenuItemCategory.DINNER,
                        10.99f,
                        "d0699af8-004e-4f43-9921-0fa7310254a0_pepperoni.jpg",
                        restaurant5
                ),
                new MenuItem(
                        null,
                        "Quattro Formaggi",
                        "Pizza z czterema rodzajami sera",
                        MenuItemCategory.DINNER,
                        11.99f,
                        "a68f6bce-e2ca-4e99-b361-2f1c80493500_quattro_formaggi.jpg",
                        restaurant5
                ),
                new MenuItem(
                        null,
                        "Capricciosa",
                        "Pizza z szynką, pieczarkami i karczochami",
                        MenuItemCategory.DINNER,
                        11.49f,
                        "a9ad46b6-ee20-448c-bc46-e7f2e945c58e_capricciosa.jpg",
                        restaurant5
                ),
                new MenuItem(
                        null,
                        "Tiramisu",
                        "Klasyczny włoski deser z biszkoptami nasączonymi kawą",
                        MenuItemCategory.DESSERT,
                        6.99f,
                        "18dbb07f-bcd4-4483-96a3-4fac57e919de_tiramisu.jpg",
                        restaurant5
                ),

                new MenuItem(
                        null,
                        "Cappuccino",
                        "Klasyczna kawa z mleczną pianką",
                        MenuItemCategory.BEVERAGE,
                        3.99f,
                        "ae00692c-257c-4f0c-9f20-067eed399f86_cappuccino.jpg",
                        restaurant6
                ),
                new MenuItem(
                        null,
                        "Latte",
                        "Kawa z mlekiem",
                        MenuItemCategory.BEVERAGE,
                        4.49f,
                        "956b05e8-0ac9-4ba0-82ed-5992b81bf18e_latte.jpg",
                        restaurant6
                ),
                new MenuItem(
                        null,
                        "Espresso",
                        "Mocna kawa",
                        MenuItemCategory.BEVERAGE,
                        2.99f,
                        "d20709f2-dfd8-4c93-9b30-826779a6d1f0_espresso.jpg",
                        restaurant6
                ),
                new MenuItem(
                        null,
                        "Croissant",
                        "Francuskie ciastko z masłem",
                        MenuItemCategory.BREAKFAST,
                        2.99f,
                        "7b6c9e95-6f3d-441f-99cd-178b458e9a99_croissant.jpg",
                        restaurant6
                ),
                new MenuItem(
                        null,
                        "Brownie",
                        "Czekoladowe ciastko",
                        MenuItemCategory.DESSERT,
                        3.99f,
                        "6e8c07b3-67ed-4beb-9d1d-f5310b727ae8_brownie.jpg",
                        restaurant6
                ),

                new MenuItem(
                        null,
                        "Sernik",
                        "Pyszny sernik z owocami",
                        MenuItemCategory.DESSERT,
                        5.99f,
                        "a131db0f-e393-43e1-bf73-9222af4cca79_sernik.jp",
                        restaurant7
                ),
                new MenuItem(
                        null,
                        "Jabłecznik",
                        "Ciasto z jabłkami",
                        MenuItemCategory.DESSERT,
                        4.99f,
                        "a91cca96-a1b9-46c4-9e17-35ed771c0585_jablecznik.jpg",
                        restaurant7
                ),
                new MenuItem(
                        null,
                        "Beza",
                        "Lekki deser z białek i cukru",
                        MenuItemCategory.DESSERT,
                        6.99f,
                        "b268ad1b-9f53-4107-97a0-ca8d91e37b66_beza.jpg",
                        restaurant7
                ),
                new MenuItem(
                        null,
                        "Tarta cytrynowa",
                        "Tarta z cytrynowym nadzieniem",
                        MenuItemCategory.DESSERT,
                        5.49f,
                        "4c344e2e-da2c-4ce5-867e-190344dfca2c_tarta_cytrynowa.jpg",
                        restaurant7
                ),
                new MenuItem(
                        null,
                        "Muffin",
                        "Czekoladowy muffin",
                        MenuItemCategory.DESSERT,
                        2.99f,
                        "1a9ffdf2-4e37-42d7-bd85-78196fb4898c_muffin.jpg",
                        restaurant7
                ),

                new MenuItem(
                        null,
                        "Curry z kurczakiem",
                        "Kurczak w sosie curry z ryżem",
                        MenuItemCategory.DINNER,
                        12.99f,
                        "b5b73fe6-50a1-4c32-8fd7-191d57194fbc_curry_z_kurczakiem.jpg",
                        restaurant8
                ),
                new MenuItem(
                        null,
                        "Chana Masala",
                        "Ciecierzyca w aromatycznym sosie pomidorowym",
                        MenuItemCategory.DINNER,
                        11.99f,
                        "550f2881-c5c4-4f30-89af-ea6b8bf82923_chana_masala.jpg",
                        restaurant8
                ),
                new MenuItem(
                        null,
                        "Tandoori",
                        "Grillowany kurczak w przyprawach",
                        MenuItemCategory.DINNER,
                        13.99f,
                        "33bdd6ab-65d6-4481-82a7-6e6bad952520_tandoori.jpg",
                        restaurant8
                ),
                new MenuItem(
                        null,
                        "Naan",
                        "Tradycyjny indyjski chleb",
                        MenuItemCategory.SNACK,
                        2.99f,
                        "f6ae7796-2635-4f89-83dc-2962b1acf2ba_naan.jpg",
                        restaurant8
                ),
                new MenuItem(
                        null,
                        "Samosa",
                        "Smażone pierożki z warzywami",
                        MenuItemCategory.SNACK,
                        4.99f,
                        "7b10d9d2-bbe1-4efb-be11-96d24b92af22_samosa.jpg",
                        restaurant8
                ),

                new MenuItem(
                        null,
                        "Gyros",
                        "Tradycyjny grecki gyros z frytkami",
                        MenuItemCategory.DINNER,
                        10.99f,
                        "d4b337e7-454b-4fbc-8068-9fea9ca550f6_gyros.jpg",
                        restaurant9
                ),
                new MenuItem(
                        null,
                        "Moussaka",
                        "Zapiekanka z bakłażanem i mięsem",
                        MenuItemCategory.DINNER,
                        11.99f,
                        "9961edf3-51c6-4c4f-b97a-b343b4b7d451_moussaka.jpg",
                        restaurant9
                ),
                new MenuItem(
                        null,
                        "Souvlaki",
                        "Grillowane szaszłyki z kurczaka",
                        MenuItemCategory.DINNER,
                        9.99f,
                        "2e9fc13a-81b4-4c6b-8597-74dbbbab593f_souvlaki.jpg",
                        restaurant9
                ),
                new MenuItem(
                        null,
                        "Choriatiki",
                        "Tradycyjna sałatka grecka",
                        MenuItemCategory.SNACK,
                        7.99f,
                        "17f1705d-1b3a-40c6-ad53-ce36d9ef9764_choriatiki.jpg",
                        restaurant9
                ),
                new MenuItem(
                        null,
                        "Baklava",
                        "Słodki deser z orzechami i miodem",
                        MenuItemCategory.DESSERT,
                        5.99f,
                        "62ba7905-c280-4d38-8d52-0320f5c14413_baklava.jpg",
                        restaurant9
                ),

                new MenuItem(
                        null,
                        "Burger z soczewicy",
                        "Wegański burger z kotletem z soczewicy",
                        MenuItemCategory.DINNER,
                        8.99f,
                        "007a952f-100f-4a8f-837f-314e0f3630ec_burger_z_soczewicy.jpg",
                        restaurant10
                ),
                new MenuItem(
                        null,
                        "Sałatka z komosą ryżową",
                        "Lekka sałatka z komosą ryżową i warzywami",
                        MenuItemCategory.SNACK,
                        7.99f,
                        "fc2fb579-471e-4cad-b499-d42cb1b8bc3d_salatka_z_komosa.jpg",
                        restaurant10
                ),
                new MenuItem(
                        null,
                        "Falafel",
                        "Smażone kuleczki z ciecierzycy",
                        MenuItemCategory.DINNER,
                        9.99f,
                        "f0546d16-ff40-4211-8a3e-79dc0cd5a9be_falafel.jpg",
                        restaurant10
                ),
                new MenuItem(
                        null,
                        "Hummus",
                        "Kremowy hummus z chlebem pita",
                        MenuItemCategory.SNACK,
                        5.99f,
                        "27b8b4ce-bbb7-4557-8326-a3df5d7582c2_hummus.jpg",
                        restaurant10
                ),
                new MenuItem(
                        null,
                        "Smoothie z owoców",
                        "Orzeźwiający napój z świeżych owoców",
                        MenuItemCategory.BEVERAGE,
                        4.99f,
                        "c6f7ce01-c43f-4e23-aba0-c679420497b0_smoothie.jpg",
                        restaurant10
                )
        );
        menuItems.forEach(menuItem -> {
            menuItem.restaurant.menuItems.add(menuItem);
            menuItemService.addMenuItem(menuItem);
        });

        List<ParcelMachine> parcelMachines = List.of(
                new ParcelMachine(
                        "WRO-001",
                        51.105550,
                        17.022090,
                        "ul. Piłsudskiego 10, 50-049 Wrocław",
                        new ArrayList<>(),
                        List.of(restaurant1, restaurant2, restaurant3, restaurant5, restaurant9)
                ),
                new ParcelMachine(
                        "WRO-002",
                        51.093760,
                        17.060650,
                        "ul. Krakowska 50, 50-425 Wrocław",
                        new ArrayList<>(),
                        List.of(restaurant4, restaurant5, restaurant6, restaurant1, restaurant10)
                ),
                new ParcelMachine(
                        "WRO-003",
                        51.100850,
                        17.037740,
                        "ul. Strzegomska 206, 50-001 Wrocław",
                        new ArrayList<>(),
                        List.of(restaurant7, restaurant8, restaurant9, restaurant3, restaurant4)
                ),
                new ParcelMachine(
                        "WRO-004",
                        51.135440,
                        17.037680,
                        "ul. Kasprowicza 5, 51-137 Wrocław",
                        new ArrayList<>(),
                        List.of(restaurant10, restaurant1, restaurant2, restaurant7, restaurant6)
                ),
                new ParcelMachine(
                        "WRO-005",
                        51.089540,
                        17.027020,
                        "ul. Kamienna 145, 53-307 Wrocław",
                        new ArrayList<>(),
                        List.of(restaurant3, restaurant4, restaurant5, restaurant8, restaurant9)
                )
        );
        parcelMachines.forEach(parcelMachineService::addParcelMachine);

        parcelMachines.forEach(parcelMachine -> {
            for (int i = 1; i <= 10; i++) {
                Locker locker = new Locker(null, false, parcelMachine);
                parcelMachine.lockers.add(locker);
                lockerService.addLocker(locker);
            }
        });

        Customer customer1 = new Customer(
                null,
                "Anna",
                "Kowalska",
                "anna.kowalska@example.com",
                "555-123-456",
                "Rynek 23, 50-101 Wrocław",
                null,
                List.of(restaurant3, restaurant4),
                List.of(parcelMachines.get(4), parcelMachines.get(0))
        );
        Customer customer2 = new Customer(
                null,
                "Jan",
                "Nowak",
                "jan.nowak@example.com",
                "321-654-987",
                "ul. Kazimierza Wielkiego 54, 50-077 Wrocław",
                null,
                List.of(restaurant5, restaurant8, restaurant9),
                List.of(parcelMachines.get(2), parcelMachines.get(3))
        );
        customerService.addCustomer(customer1);
        customerService.addCustomer(customer2);

        Random random = new Random();
        Order order1 = new Order(
                null,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(random.nextInt(30) + 60),
                LocalDateTime.now().plusMinutes(random.nextInt(30) + 120),
                OrderStatus.PREPARED,
                false,
                customer1,
                parcelMachines.get(4).lockers.getFirst(),
                null
        );
        Order order2 = new Order(
                null,
                LocalDateTime.now().plusMinutes(-60),
                LocalDateTime.now().plusMinutes(random.nextInt(30) - 60),
                LocalDateTime.now().plusMinutes(random.nextInt(30) - 120),
                OrderStatus.PREPARED,
                false,
                customer2,
                parcelMachines.get(2).lockers.getFirst(),
                null
        );
        Order order3 = new Order(
                null,
                LocalDateTime.now().plusMinutes(-60),
                LocalDateTime.now().plusMinutes(random.nextInt(30) - 60),
                LocalDateTime.now().plusMinutes(random.nextInt(30) - 120),
                OrderStatus.RECEIVED,
                false,
                customer1,
                parcelMachines.get(1).lockers.getFirst(),
                null
        );
        Order order4 = new Order(
                null,
                LocalDateTime.now().plusMinutes(-60),
                LocalDateTime.now().plusMinutes(random.nextInt(30) - 60),
                LocalDateTime.now().plusMinutes(random.nextInt(30) - 120),
                OrderStatus.RECEIVED,
                false,
                customer1,
                parcelMachines.get(2).lockers.getFirst(),
                null
        );
        Order order5 = new Order(
                null,
                LocalDateTime.now().plusMinutes(-60),
                LocalDateTime.now().plusMinutes(random.nextInt(30) - 60),
                LocalDateTime.now().plusMinutes(random.nextInt(30) - 120),
                OrderStatus.RECEIVED,
                false,
                customer1,
                parcelMachines.get(3).lockers.getFirst(),
                null
        );
        orderService.addOrder(order1);
        orderService.addOrder(order2);
        orderService.addOrder(order3);
        orderService.addOrder(order4);
        orderService.addOrder(order5);

        List<OrderItem> orderItems = List.of(
                new OrderItem(null, 2, restaurant1.menuItems.get(0), order1),
                new OrderItem(null, 1, restaurant1.menuItems.get(3), order1),
                new OrderItem(null, 3, restaurant1.menuItems.get(1), order1),
                new OrderItem(null, 1, restaurant10.menuItems.get(0), order2),
                new OrderItem(null, 1, restaurant10.menuItems.get(2), order2),
                new OrderItem(null, 2, restaurant10.menuItems.get(4), order2),
                new OrderItem(null, 3, restaurant10.menuItems.get(1), order2),
                new OrderItem(null, 2, restaurant2.menuItems.get(0), order3),
                new OrderItem(null, 1, restaurant2.menuItems.get(3), order3),
                new OrderItem(null, 3, restaurant2.menuItems.get(1), order3),
                new OrderItem(null, 2, restaurant3.menuItems.get(0), order4),
                new OrderItem(null, 1, restaurant3.menuItems.get(3), order4),
                new OrderItem(null, 3, restaurant3.menuItems.get(1), order4),
                new OrderItem(null, 2, restaurant6.menuItems.get(0), order5),
                new OrderItem(null, 1, restaurant6.menuItems.get(3), order5),
                new OrderItem(null, 3, restaurant6.menuItems.get(1), order5)
        );
        orderItems.forEach(orderItemService::addOrderItem);
    }
}
