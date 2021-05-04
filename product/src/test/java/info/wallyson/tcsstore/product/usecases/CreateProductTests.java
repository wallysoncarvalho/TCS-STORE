package info.wallyson.tcsstore.product.usecases;

import info.wallyson.tcsstore.product.domain.Product;
import info.wallyson.tcsstore.product.exception.InvalidProductPriceException;
import info.wallyson.tcsstore.product.util.ProductCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

final class CreateProductTests extends ProductTestBaseClass {
  private CreateProduct createProduct;

  @BeforeEach
  void setUp() {
    this.createProduct = new CreateProduct(productRepository);
  }

  @Test
  @DisplayName("Should throw exception when creating product with negative price")
  void shouldThrowExceptionWhenCreatingProductWithNegativePrice() {
    var product = new Product(1L, "description", "soap", new BigDecimal("-1"));

    assertThrows(InvalidProductPriceException.class, () -> createProduct.createProduct(product));
  }

  @Test
  @DisplayName("Should throw exception when creating product price of zero")
  void shouldThrowExceptionWhenCreatingProductWithPriceOfZero() {
    var product = new Product(1L, "description", "soap", BigDecimal.ZERO);

    assertThrows(InvalidProductPriceException.class, () -> createProduct.createProduct(product));
  }

  @Test
  @DisplayName("Should save new product")
  void shouldSaveNewProduct() {
    var product = ProductCreator.create();

    createProduct.createProduct(product);

    verify(productRepository, Mockito.times(1)).save(product);
  }
}
