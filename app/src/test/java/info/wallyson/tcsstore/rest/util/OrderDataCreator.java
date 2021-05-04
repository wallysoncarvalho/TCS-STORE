package info.wallyson.tcsstore.rest.util;

import info.wallyson.tcsstore.order.domain.ClientData;
import info.wallyson.tcsstore.order.domain.CreateOrderData;
import info.wallyson.tcsstore.order.domain.ProductData;
import info.wallyson.tcsstore.order.domain.ShippingData;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class OrderDataCreator {
  private static final String clientCPF = "11122233343";

  public static CreateOrderData getCreateOrderData() {
    var itemDataSet =
        Set.of(
            new CreateOrderData.CreateOrderItemData(1L, 2L),
            new CreateOrderData.CreateOrderItemData(2L, 3L));
    return new CreateOrderData(clientCPF, itemDataSet);
  }

  public static ClientData getClientData() {
    return new ClientData(clientCPF, "tiringa", "29090420");
  }

  public static ShippingData getShippingData() {
    return new ShippingData("MG", "Minas Gerais", BigDecimal.TEN);
  }

  public static List<ProductData> getProductDataList() {
    return List.of(
        new ProductData(1L, "product 1", new BigDecimal("10.50")),
        new ProductData(2L, "product 2", new BigDecimal("5.50")));
  }
}
