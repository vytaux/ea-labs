package repositories;

import domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    Order findByOrderNumber(String orderNumber);

    @Query("SELECT o.orderNumber FROM Order o WHERE o.status = 'closed'")
    List<String> findByOrderNumbersOfOrdersWithStatusClosed();

    @Query("SELECT o.orderNumber FROM Order o WHERE o.customer.address.city = :city")
    List<String> findOrderNumbersByCity(@Param("city") String city);
}
