package info.wallyson.tcsstore.order.domain;

import java.util.Set;

public class OrderReceipt {
  private final ClientData client;
  private final Set<OrderItem> products;
  private final ShippingData shipping;

  public OrderReceipt(ClientData client, Set<OrderItem> products, ShippingData shipping) {
    this.client = client;
    this.products = products;
    this.shipping = shipping;
  }

  public ClientData getClient() {
    return client;
  }

  public Set<OrderItem> getProducts() {
    return products;
  }

  public ShippingData getShipping() {
    return shipping;
  }
}
