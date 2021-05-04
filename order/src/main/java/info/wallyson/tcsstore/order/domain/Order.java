package info.wallyson.tcsstore.order.domain;

import info.wallyson.tcsstore.order.exception.NotEnoughOrderItemsException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class Order {
  private final Long orderId;
  private final LocalDate orderDate;
  private final String client;
  private final BigDecimal totalPrice;
  private final Set<OrderItem> orderItems;
  private final BigDecimal shippingPrice;

  public Order(Long orderId, String client, Set<OrderItem> orderItems, BigDecimal shippingPrice) {

    if (Objects.isNull(orderItems) || orderItems.size() == 0)
      throw new NotEnoughOrderItemsException();

    this.orderId = orderId;
    this.orderDate = LocalDate.now();
    this.client = client;
    this.totalPrice = calculateTotalPrice(orderItems);
    this.orderItems = orderItems;
    this.shippingPrice = shippingPrice.setScale(2, RoundingMode.HALF_EVEN);
  }

  public Long getOrderId() {
    return orderId;
  }

  public LocalDate getOrderDate() {
    return orderDate;
  }

  public String getClient() {
    return client;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public Set<OrderItem> getOrderItems() {
    return orderItems;
  }

  public BigDecimal getShippingPrice() {
    return shippingPrice;
  }

  private BigDecimal calculateTotalPrice(Set<OrderItem> orderItemList) {
    return orderItemList.stream()
        .map(
            orderItem ->
                orderItem.getProductPrice().multiply(BigDecimal.valueOf(orderItem.getAmount())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
