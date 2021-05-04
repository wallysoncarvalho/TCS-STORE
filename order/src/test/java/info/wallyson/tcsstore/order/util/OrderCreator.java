package info.wallyson.tcsstore.order.util;

import info.wallyson.tcsstore.order.domain.Order;
import info.wallyson.tcsstore.order.domain.OrderItem;

import java.math.BigDecimal;
import java.util.Set;

public class OrderCreator {

  public static Order create() {
    var orderItems = Set.of(new OrderItem(1L, 2L, BigDecimal.TEN));

    return new Order(10L, "11122233345", orderItems, BigDecimal.ONE);
  }
}
