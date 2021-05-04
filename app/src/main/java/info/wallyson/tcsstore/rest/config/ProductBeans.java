package info.wallyson.tcsstore.rest.config;

import info.wallyson.tcsstore.product.ports.ProductRepository;
import info.wallyson.tcsstore.product.usecases.CreateProduct;
import info.wallyson.tcsstore.product.usecases.DeleteProduct;
import info.wallyson.tcsstore.product.usecases.GetProduct;
import info.wallyson.tcsstore.product.usecases.UpdateProduct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductBeans {
  private final ProductRepository productRepository;

  public ProductBeans(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Bean
  public CreateProduct createProductBean() {
    return new CreateProduct(productRepository);
  }

  @Bean
  public GetProduct getProductBean() {
    return new GetProduct(productRepository);
  }

  @Bean
  public UpdateProduct updateProductBean() {
    return new UpdateProduct(productRepository);
  }

  @Bean
  public DeleteProduct deleteProductBean() {
    return new DeleteProduct(productRepository);
  }
}
