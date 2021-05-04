package info.wallyson.tcsstore.order.ports;

import info.wallyson.tcsstore.order.domain.ProductData;

import java.util.Optional;

public interface ProductService {
  Optional<ProductData> getProductById(Long productId);
}
