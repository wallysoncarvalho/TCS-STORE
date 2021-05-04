package info.wallyson.tcsstore.product.exception;

public class ProductDontExistException extends ProductBaseException {
  public ProductDontExistException(Long id) {
    super(String.format("Product with id '%s' do not exist.", id));
  }
}
