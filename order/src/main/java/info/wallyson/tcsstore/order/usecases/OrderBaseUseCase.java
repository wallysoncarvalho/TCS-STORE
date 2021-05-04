package info.wallyson.tcsstore.order.usecases;

import info.wallyson.tcsstore.order.ports.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class OrderBaseUseCase {
  final Logger logger = LoggerFactory.getLogger(OrderBaseUseCase.class);
  final OrderRepository orderRepository;

  OrderBaseUseCase(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }
}
