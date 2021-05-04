package info.wallyson.tcsstore.product.usecases;

import info.wallyson.tcsstore.product.ports.ProductRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
abstract class ProductTestBaseClass {
  @Mock ProductRepository productRepository;
}
