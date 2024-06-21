package org.vuelosGlobales.salesAgent.customer.infrastructure;

import org.vuelosGlobales.salesAgent.customer.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    void save(Customer customer);
    void update(Customer customer);
    Optional<Customer> findById(int id);
    List<Customer> findAll();
    void delete(int id);
}
