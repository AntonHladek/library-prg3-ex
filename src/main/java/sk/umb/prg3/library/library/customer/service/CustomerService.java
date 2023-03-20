package sk.umb.prg3.library.library.customer.service;

import org.springframework.stereotype.Service;
import sk.umb.prg3.library.library.customer.persistence.entity.CustomerEntity;
import sk.umb.prg3.library.library.customer.persistence.repository.CustomerRepository;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void createCustomer(CustomerDetailDto customer) {
        CustomerEntity ce = new CustomerEntity();
        ce.setFirstName(customer.getFirstName());
        ce.setLastName(customer.getLastName());
        customerRepository.save(ce);
    }


}
