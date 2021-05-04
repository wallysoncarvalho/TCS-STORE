package info.wallyson.tcsstore.order.exception;

public class ShippingDestinationException extends BaseOrderException {
  public ShippingDestinationException(String cep) {
    super(String.format("Shipping destination '%s' not found", cep));
  }
}
