package info.wallyson.tcsstore.product.usecases;

import info.wallyson.tcsstore.product.ports.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class ProductBaseUseCase {
  final Logger logger = LoggerFactory.getLogger(ProductBaseUseCase.class);

  public final ProductRepository productRepository;

  public ProductBaseUseCase(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }
}
