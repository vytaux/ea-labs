package repositories;

import domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
    Order findByOrderNumber(String orderNumber);
}
