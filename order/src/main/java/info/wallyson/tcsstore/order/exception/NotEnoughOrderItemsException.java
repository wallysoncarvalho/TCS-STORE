package info.wallyson.tcsstore.order.exception;

public class NotEnoughOrderItemsException extends BaseOrderException {
  public NotEnoughOrderItemsException() {
    super("There must exist at least one item to create an order.");
  }
}
