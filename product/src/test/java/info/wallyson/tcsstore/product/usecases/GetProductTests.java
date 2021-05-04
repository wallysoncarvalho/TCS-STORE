package info.wallyson.tcsstore.product.usecases;

import info.wallyson.tcsstore.product.exception.ProductDontExistException;
import info.wallyson.tcsstore.product.util.ProductCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

final class GetProductTests extends ProductTestBaseClass {
  private GetProduct getProduct;

  @BeforeEach
  void setUp() {
    this.getProduct = new GetProduct(productRepository);
  }

  @Test
  @DisplayName("Should throw exception when product do not exists")
  void shouldThrowExceptionWhenProductDontExists() {
    var product = ProductCreator.create();

    when(productRepository.findById(product.getId())).thenReturn(Optional.empty());

    assertThrows(ProductDontExistException.class, () -> getProduct.getProduct(product.getId()));
    verify(productRepository, times(1)).findById(product.getId());
  }

  @Test
  @DisplayName("Should get an existing product")
  void shouldGetExistingProduct() {
    var product = ProductCreator.create();

    when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

    var savedProduct = getProduct.getProduct(product.getId());

    assertEquals(product.getName(), savedProduct.getName());
    assertEquals(product.getDescription(), savedProduct.getDescription());
    verify(productRepository, times(1)).findById(product.getId());
  }
}
