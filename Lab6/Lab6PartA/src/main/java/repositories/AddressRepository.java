package repositories;

import domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, String> {
    @Query(value = "SELECT * FROM Address a WHERE a.city = 'Amsterdam'", nativeQuery = true)
    List<Address> findAllAddressesInAmsterdam();
}
