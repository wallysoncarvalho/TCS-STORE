package info.wallyson.tcsstore.rest.controller.order;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderItemRequest {
  private final Long productId;
  private final Long amount;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public OrderItemRequest(
      @JsonProperty(value = "product_id", required = true) Long productId,
      @JsonProperty(value = "amount", required = true) Long amount) {
    this.productId = productId;
    this.amount = amount;
  }

  @JsonProperty("product_id")
  public Long getProductId() {
    return productId;
  }

  public Long getAmount() {
    return amount;
  }
}
