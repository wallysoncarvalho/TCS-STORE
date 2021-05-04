package info.wallyson.tcsstore.product.usecases;

import info.wallyson.tcsstore.product.exception.ProductDontExistException;
import info.wallyson.tcsstore.product.ports.ProductRepository;

public class DeleteProduct extends ProductBaseUseCase {
  public DeleteProduct(ProductRepository productRepository) {
    super(productRepository);
  }

  public void delete(Long productId) {
    if (!productRepository.existsById(productId)) throw new ProductDontExistException(productId);

    productRepository.deleteById(productId);

    logger.info("Product with ID {} deleted.", productId);
  }
}
