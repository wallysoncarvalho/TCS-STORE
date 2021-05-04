package info.wallyson.tcsstore.persistence.order;

import info.wallyson.tcsstore.order.domain.OrderItem;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
public class OrderItemEntity {
  @Id @GeneratedValue private Long id;

  @ManyToOne
  private OrderEntity order;

  @Column(name = "product_id")
  private Long productId;

  @Column(name = "amount")
  private Long amount;

  @Column(name = "product_price")
  private BigDecimal productPrice;

  public OrderItem toOrderItem() {
    return new OrderItem(productId, amount, productPrice);
  }

  public static OrderItemEntity fromOrderItem(OrderItem orderItem) {
    var orderItemEntity = new OrderItemEntity();
    orderItemEntity.setProductId(orderItem.getProductId());
    orderItemEntity.setAmount(orderItem.getAmount());
    orderItemEntity.setProductPrice(orderItem.getProductPrice());
    return orderItemEntity;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public OrderEntity getOrder() {
    return order;
  }

  public void setOrder(OrderEntity order) {
    this.order = order;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public BigDecimal getProductPrice() {
    return productPrice;
  }

  public void setProductPrice(BigDecimal productPrice) {
    this.productPrice = productPrice;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OrderItemEntity that = (OrderItemEntity) o;
    return id != null && id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
