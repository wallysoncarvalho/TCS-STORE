package info.wallyson.tcsstore.rest.util;

import info.wallyson.tcsstore.rest.controller.order.CreateOrderRequest;
import info.wallyson.tcsstore.rest.controller.order.OrderItemRequest;

import java.util.Set;

public class CreateOrderRequestCreator {

  public static CreateOrderRequest create() {
    var orderItems = Set.of(new OrderItemRequest(1L, 2L), new OrderItemRequest(2L, 3L));

    return new CreateOrderRequest("55028292000", orderItems);
  }
}
