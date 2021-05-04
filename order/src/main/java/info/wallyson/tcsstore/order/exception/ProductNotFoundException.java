package info.wallyson.tcsstore.order.exception;

public class ProductNotFoundException extends BaseOrderException {
  public ProductNotFoundException(Long productId) {
    super(String.format("Product with ID '%s' not found.", productId));
  }
}
