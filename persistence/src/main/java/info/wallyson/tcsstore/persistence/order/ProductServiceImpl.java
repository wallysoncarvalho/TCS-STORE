package info.wallyson.tcsstore.persistence.order;

import info.wallyson.tcsstore.order.domain.ProductData;
import info.wallyson.tcsstore.order.ports.ProductService;
import info.wallyson.tcsstore.product.ports.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public Optional<ProductData> getProductById(Long productId) {
    return productRepository
        .findById(productId)
        .map(
            product ->
                new ProductData(product.getId(), product.getDescription(), product.getPriceUnit()));
  }
}
