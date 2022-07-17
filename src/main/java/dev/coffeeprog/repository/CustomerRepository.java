package dev.coffeeprog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import dev.coffeeprog.model.Customer;

@Repository
public interface CustomerRepository  extends CrudRepository<Customer, Long> {
    List<Customer> findByEmail(String email);
}
