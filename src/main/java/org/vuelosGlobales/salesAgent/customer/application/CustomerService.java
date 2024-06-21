package org.vuelosGlobales.salesAgent.customer.application;

import org.vuelosGlobales.salesAgent.customer.infrastructure.CustomerRepository;
import org.vuelosGlobales.salesAgent.customer.domain.Customer;

import java.util.List;
import java.util.Optional;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void createCustomer(Customer customer){
        this.customerRepository.save(customer);
    }

    public void updateCustomer(Customer customer){
        this.customerRepository.update(customer);
    }

    public Optional<Customer> getCustomerById(int id){
        return this.customerRepository.findById(id);
    }

    public List<Customer> getAllCustomers(){
        return this.customerRepository.findAll();
    }

    public void deleteCustomer(int id){
        this.customerRepository.delete(id);
    }
}
