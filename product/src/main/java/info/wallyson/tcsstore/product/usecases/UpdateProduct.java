package info.wallyson.tcsstore.product.usecases;

import info.wallyson.tcsstore.product.domain.Product;
import info.wallyson.tcsstore.product.exception.ProductDontExistException;
import info.wallyson.tcsstore.product.ports.ProductRepository;

public class UpdateProduct extends ProductBaseUseCase {
  public UpdateProduct(ProductRepository productRepository) {
    super(productRepository);
  }

  public void update(Product productNewData) {
    if (!productRepository.existsById(productNewData.getId()))
      throw new ProductDontExistException(productNewData.getId());

    productRepository.updateProduct(productNewData);

    logger.info("Product {} updated", productNewData.getName());
  }
}
