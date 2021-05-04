package info.wallyson.tcsstore.product.ports;

import info.wallyson.tcsstore.product.domain.Product;

import java.util.Optional;

public interface ProductRepository {
  Optional<Product> findById(Long id);

  boolean existsById(Long id);

  Product save(Product product);

  void updateProduct(Product productNewData);

  void deleteById(Long id);
}
