package info.wallyson.tcsstore.product.usecases;

import info.wallyson.tcsstore.product.exception.ProductDontExistException;
import info.wallyson.tcsstore.product.util.ProductCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

final class UpdateProductTests extends ProductTestBaseClass {
  private UpdateProduct updateProduct;

  @BeforeEach
  void setUp() {
    this.updateProduct = new UpdateProduct(productRepository);
  }

  @Test
  @DisplayName("Should throw exception when product do not exists")
  void shouldThrowExceptionWhenProductDontExists() {
    var product = ProductCreator.create();

    when(productRepository.existsById(product.getId())).thenReturn(false);

    assertThrows(ProductDontExistException.class, () -> updateProduct.update(product));
    verify(productRepository, times(1)).existsById(product.getId());
  }

  @Test
  @DisplayName("Should update an existing product")
  void shouldUpdateAnExistingProduct() {
    var product = ProductCreator.create();

    when(productRepository.existsById(product.getId())).thenReturn(true);

    updateProduct.update(product);

    verify(productRepository, times(1)).updateProduct(product);
    verify(productRepository, times(1)).existsById(product.getId());
  }
}
