package info.wallyson.tcsstore.persistence.order;

import info.wallyson.tcsstore.order.domain.Order;
import info.wallyson.tcsstore.order.ports.OrderRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
  private final OrderRepositoryJpaAdapter orderRepositoryJpaAdapter;

  public OrderRepositoryImpl(OrderRepositoryJpaAdapter orderRepositoryJpaAdapter) {
    this.orderRepositoryJpaAdapter = orderRepositoryJpaAdapter;
  }

  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  @Override
  public Optional<Order> findById(Long id) {
    var orderEntity = orderRepositoryJpaAdapter.findById(id);
    return orderEntity.map(OrderEntity::toOrder);
  }

  @Override
  public boolean existsById(Long id) {
    return orderRepositoryJpaAdapter.existsById(id);
  }

  @Transactional
  @Override
  public Order save(Order order) {
    var orderEntity = OrderEntity.fromOrder(order);
    var savedOrderEntity = orderRepositoryJpaAdapter.save(orderEntity);
    return savedOrderEntity.toOrder();
  }
}
