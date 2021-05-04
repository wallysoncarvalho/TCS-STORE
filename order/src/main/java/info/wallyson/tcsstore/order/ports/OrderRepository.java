package info.wallyson.tcsstore.order.ports;

import info.wallyson.tcsstore.order.domain.Order;

import java.util.Optional;

public interface OrderRepository {
  Optional<Order> findById(Long id);

  boolean existsById(Long id);

  Order save(Order order);
}
