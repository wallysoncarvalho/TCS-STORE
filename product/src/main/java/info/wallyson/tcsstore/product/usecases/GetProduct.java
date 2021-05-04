package info.wallyson.tcsstore.product.usecases;

import info.wallyson.tcsstore.product.domain.Product;
import info.wallyson.tcsstore.product.exception.ProductDontExistException;
import info.wallyson.tcsstore.product.ports.ProductRepository;

public class GetProduct extends ProductBaseUseCase {
  public GetProduct(ProductRepository productRepository) {
    super(productRepository);
  }

  public Product getProduct(Long id) {
    logger.info("Retrieving product with ID {}", id);
    return productRepository.findById(id).orElseThrow(() -> new ProductDontExistException(id));
  }
}
