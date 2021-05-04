package info.wallyson.tcsstore.order.usecases;

import info.wallyson.tcsstore.order.domain.Order;
import info.wallyson.tcsstore.order.exception.OrderDontExistException;
import info.wallyson.tcsstore.order.ports.OrderRepository;

public class GetOrder extends OrderBaseUseCase {
  public GetOrder(OrderRepository orderRepository) {
    super(orderRepository);
  }

  public Order getOrder(Long id) {
    logger.info("Retrieving order of ID {}", id);
    return orderRepository.findById(id).orElseThrow(() -> new OrderDontExistException(id));
  }
}
