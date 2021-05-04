package info.wallyson.tcsstore.order.domain;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public class CreateOrderData {
  private final String clientCpf;
  private final Set<CreateOrderItemData> createOrderItemData;

  public CreateOrderData(String clientCpf, Set<CreateOrderItemData> createOrderItemData) {
    this.clientCpf = clientCpf;
    this.createOrderItemData = Collections.unmodifiableSet(createOrderItemData);
  }

  public String getClientCpf() {
    return clientCpf;
  }

  public Set<CreateOrderItemData> getOrderItemsData() {
    return createOrderItemData;
  }

  public static class CreateOrderItemData {
    private final Long productId;
    private final Long amount;

    public CreateOrderItemData(Long productId, Long amount) {
      this.productId = productId;
      this.amount = amount;
    }

    public Long getProductId() {
      return productId;
    }

    public Long getAmount() {
      return amount;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      CreateOrderItemData that = (CreateOrderItemData) o;
      return productId.equals(that.productId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(productId);
    }
  }
}
