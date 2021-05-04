package info.wallyson.tcsstore.persistence.order;

import info.wallyson.tcsstore.order.domain.Order;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "order_store")
public class OrderEntity {
  @Id @GeneratedValue private Long id;

  @Column(name = "order_date")
  private LocalDate orderDate;

  @Column(name = "client")
  private String client;

  @Column(name = "total_price", scale = 2)
  private BigDecimal totalPrice;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<OrderItemEntity> orderItems = new HashSet<>();

  @Column(name = "shipping_price", scale = 2)
  private BigDecimal shippingPrice;

  public void addOrderItem(OrderItemEntity orderItemEntity) {
    orderItems.add(orderItemEntity);
    orderItemEntity.setOrder(this);
  }

  public void removeOrderItem(OrderItemEntity orderItemEntity) {
    orderItems.remove(orderItemEntity);
    orderItemEntity.setOrder(null);
  }

  public Order toOrder() {
    var orderItemList =
        this.orderItems.stream().map(OrderItemEntity::toOrderItem).collect(Collectors.toSet());
    return new Order(id, client, orderItemList, shippingPrice);
  }

  public static OrderEntity fromOrder(Order order) {
    var orderEntity = new OrderEntity();
    var orderItemEntityList =
        order.getOrderItems().stream()
            .map(OrderItemEntity::fromOrderItem)
            .collect(Collectors.toSet());

    orderEntity.setOrderDate(order.getOrderDate());
    orderEntity.setClient(order.getClient());
    orderEntity.setTotalPrice(order.getTotalPrice());

    orderItemEntityList.forEach(orderEntity::addOrderItem);

    orderEntity.setShippingPrice(order.getShippingPrice());
    return orderEntity;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(LocalDate orderDate) {
    this.orderDate = orderDate;
  }

  public String getClient() {
    return client;
  }

  public void setClient(String client) {
    this.client = client;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  public Set<OrderItemEntity> getOrderItems() {
    return orderItems;
  }

  public BigDecimal getShippingPrice() {
    return shippingPrice;
  }

  public void setShippingPrice(BigDecimal shippingPrice) {
    this.shippingPrice = shippingPrice;
  }
}
