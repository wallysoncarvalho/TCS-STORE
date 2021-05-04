package info.wallyson.tcsstore.order.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class OrderItem {
  private final Long productId;
  private final Long amount;
  private final BigDecimal productPrice;

  public OrderItem(Long productId, Long amount, BigDecimal productPrice) {
    this.productId = productId;
    this.amount = amount;
    this.productPrice = productPrice.setScale(2, RoundingMode.HALF_EVEN);
  }

  public Long getProductId() {
    return productId;
  }

  public Long getAmount() {
    return amount;
  }

  public BigDecimal getProductPrice() {
    return productPrice;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OrderItem orderItem = (OrderItem) o;
    return productId.equals(orderItem.productId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId);
  }
}
