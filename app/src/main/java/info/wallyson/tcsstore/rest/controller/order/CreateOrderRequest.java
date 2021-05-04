package info.wallyson.tcsstore.rest.controller.order;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import info.wallyson.tcsstore.order.domain.CreateOrderData;

import java.util.Set;
import java.util.stream.Collectors;

public class CreateOrderRequest {
  private final String clientCpf;
  private final Set<OrderItemRequest> orderItemsRequest;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public CreateOrderRequest(
      @JsonProperty(value = "client_cpf", required = true) String clientCpf,
      @JsonProperty(value = "items", required = true) Set<OrderItemRequest> orderItemsRequest) {
    this.clientCpf = clientCpf;
    this.orderItemsRequest = orderItemsRequest;
  }

  public CreateOrderData toOrderData() {
    var orderItemDataSet =
        orderItemsRequest.stream()
            .map(
                item ->
                    new CreateOrderData.CreateOrderItemData(item.getProductId(), item.getAmount()))
            .collect(Collectors.toSet());

    return new CreateOrderData(clientCpf, orderItemDataSet);
  }

  @JsonProperty("client_cpf")
  public String getClientCpf() {
    return clientCpf;
  }

  @JsonProperty("items")
  public Set<OrderItemRequest> getOrderItemsRequest() {
    return orderItemsRequest;
  }
}
