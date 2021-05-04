package info.wallyson.tcsstore.product.usecases;

import info.wallyson.tcsstore.product.exception.ProductDontExistException;
import info.wallyson.tcsstore.product.util.ProductCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

final class DeleteProductTests extends ProductTestBaseClass {
  private DeleteProduct deleteProduct;

  @BeforeEach
  void setUp() {
    this.deleteProduct = new DeleteProduct(productRepository);
  }

  @Test
  @DisplayName("Should throw exception when product do not exists")
  void shouldThrowExceptionWhenProductDontExists() {
    var product = ProductCreator.create();

    when(productRepository.existsById(product.getId())).thenReturn(false);

    assertThrows(ProductDontExistException.class, () -> deleteProduct.delete(product.getId()));
    verify(productRepository, times(1)).existsById(product.getId());
  }

  @Test
  @DisplayName("Should delete product by its ID")
  void shouldDeleteProductByID() {
    var product = ProductCreator.create();

    when(productRepository.existsById(product.getId())).thenReturn(true);

    deleteProduct.delete(product.getId());
    verify(productRepository, times(1)).existsById(product.getId());
    verify(productRepository, times(1)).deleteById(product.getId());
  }
}
