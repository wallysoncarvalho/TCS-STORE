package info.wallyson.tcsstore.order.domain;

import info.wallyson.tcsstore.order.exception.NotEnoughOrderItemsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class OrderTests {

  @Test
  @DisplayName("Should return exception when tryin to create order without products")
  void shouldReturnExceptionWhenOrderHasNoProducts() {
    assertThrows(
        NotEnoughOrderItemsException.class,
        () -> new Order(10L, "11122233345", Set.of(), BigDecimal.ONE));
  }

  @Test
  @DisplayName("Should test instance variables set on creation")
  void shouldTestInstanceVariablesSetOnCreation() {
    var orderItems = Set.of(new OrderItem(1L, 2L, BigDecimal.TEN));
    var order = new Order(10L, "11122233345", orderItems, BigDecimal.ONE);

    assertEquals(new BigDecimal("20.00"), order.getTotalPrice());
    assertEquals(new BigDecimal("1.00"), order.getShippingPrice());
    assertEquals(LocalDate.now(), order.getOrderDate());
  }
}
