package info.wallyson.tcsstore.order.domain;

import java.math.BigDecimal;

public class ProductData {
  private final Long productId;
  private final String name;
  private final BigDecimal price;
  private Long amount;

  public ProductData(Long productId, String name, BigDecimal price) {
    this.productId = productId;
    this.name = name;
    this.price = price;
  }

  public OrderItem toOrderItem() {
    return new OrderItem(productId, amount, price);
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }
}
