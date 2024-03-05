package repositories;

import domain.Customer;
import domain.Order;
import jakarta.persistence.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAll();
    List<Customer> findAllCustomersFromUSA();

    @Query("SELECT c.firstName, c.lastName FROM Customer c WHERE c.address.city = 'Amsterdam'")
    List<String[]> findFirstAndLastNamesOfCustomersFromAmsterdam();
}
