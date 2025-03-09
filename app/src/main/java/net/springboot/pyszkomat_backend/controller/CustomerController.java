package net.springboot.pyszkomat_backend.controller;

import net.springboot.pyszkomat_backend.dto.crud.CustomerCrudDto;
import net.springboot.pyszkomat_backend.model.Customer;
import net.springboot.pyszkomat_backend.service.CustomerService;
import net.springboot.pyszkomat_backend.service.OrderService;
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
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;
    private final RestaurantService restaurantService;
    private final ParcelMachineService parcelMachineService;

    public CustomerController(CustomerService customerService, OrderService orderService, RestaurantService restaurantService, ParcelMachineService parcelMachineService) {
        this.customerService = customerService;
        this.orderService = orderService;
        this.restaurantService = restaurantService;
        this.parcelMachineService = parcelMachineService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerCrudDto>> getAllCustomers() {
        List<CustomerCrudDto> customers = new ArrayList<>();

        for (Customer customer : customerService.getCustomers()) {
            customers.add(new CustomerCrudDto(customer));
        }

        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerCrudDto> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(new CustomerCrudDto(customerService.getCustomer(id)));
    }

    @PostMapping
    public ResponseEntity<CustomerCrudDto> createCustomer(@RequestBody CustomerCrudDto customerDTO) {
        customerDTO.ordersIds = new ArrayList<>();

        Customer newCustomer = customerService.addCustomer(
                customerDTO.toCustomer(orderService, restaurantService, parcelMachineService)
        );

        return ResponseEntity.ok(new CustomerCrudDto(newCustomer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerCrudDto> updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerCrudDto customerDTO
    ) {
        customerDTO.ordersIds = new ArrayList<>();

        Customer updatedCustomer = customerService.updateCustomer(
                id, customerDTO.toCustomer(orderService, restaurantService, parcelMachineService)
        );

        return ResponseEntity.ok(new CustomerCrudDto(updatedCustomer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
