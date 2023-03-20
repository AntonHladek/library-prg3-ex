package sk.umb.prg3.library.library.customer.controller;

import org.springframework.web.bind.annotation.*;
import sk.umb.prg3.library.library.customer.service.CustomerDetailDto;
import sk.umb.prg3.library.library.customer.service.CustomerService;

@RestController

public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/api/customers/{customerId}")
    public CustomerDetailDto getCustomer(@PathVariable Long customerId) {
        System.out.println("vraciam zakaznika c. " + customerId);
        CustomerDetailDto ret = new CustomerDetailDto();

        ret.setFirstName("Janko");
        ret.setLastName("Hrasko");

        return ret;
    }

    @PostMapping("/api/customers")
    public void createCustomer(@RequestBody CustomerDetailDto customer) {
        System.out.println("idem2...");
        System.out.println(customer.toString());
        customerService.createCustomer(customer);
    }
}
