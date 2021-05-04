package info.wallyson.tcsstore.order.usecases;

import info.wallyson.tcsstore.order.exception.OrderDontExistException;
import info.wallyson.tcsstore.order.util.OrderCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

final class GetOrderTests extends OrderTestBaseClass {
  private GetOrder getOrder;

  @BeforeEach
  void setUp() {
    this.getOrder = new GetOrder(orderRepository);
  }

  @Test
  @DisplayName("Should throw exception when order do not exists")
  void shouldThrowExceptionWhenOrderDontExists() {
    var orderId = 1L;

    when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

    assertThrows(OrderDontExistException.class, () -> getOrder.getOrder(orderId));
    verify(orderRepository, times(1)).findById(orderId);
  }

  @Test
  @DisplayName("Should get existing order")
  void shouldGetExistingOrder() {
    var order = OrderCreator.create();

    when(orderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));

    var orderRetrieved = getOrder.getOrder(order.getOrderId());

    assertEquals(order.getOrderId(), orderRetrieved.getOrderId());
    assertEquals(order.getOrderDate(), orderRetrieved.getOrderDate());
    assertEquals(order.getOrderItems().size(), orderRetrieved.getOrderItems().size());
    assertEquals(order.getClient(), order.getClient());
    assertEquals(order.getTotalPrice(), orderRetrieved.getTotalPrice());
    assertEquals(order.getShippingPrice(), orderRetrieved.getShippingPrice());
    verify(orderRepository, times(1)).findById(order.getOrderId());
  }
}
