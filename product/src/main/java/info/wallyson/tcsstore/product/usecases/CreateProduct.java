package info.wallyson.tcsstore.product.usecases;

import info.wallyson.tcsstore.product.domain.Product;
import info.wallyson.tcsstore.product.exception.InvalidProductPriceException;
import info.wallyson.tcsstore.product.ports.ProductRepository;

import java.math.BigDecimal;

public class CreateProduct extends ProductBaseUseCase {
  public CreateProduct(ProductRepository productRepository) {
    super(productRepository);
  }

  public void createProduct(Product product) {
    if (product.getPriceUnit().compareTo(BigDecimal.ZERO) <= 0)
      throw new InvalidProductPriceException(product.getPriceUnit());

    productRepository.save(product);

    logger.info("New product created with name {}", product.getName());
  }
}
