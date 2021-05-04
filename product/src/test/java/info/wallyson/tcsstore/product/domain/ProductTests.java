package info.wallyson.tcsstore.product.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class ProductTests {

  @Test
  @DisplayName("Should create product with price scale of 2")
  void shouldCreateProductWithPriceScaleOfTwo() {
    var product = new Product(1L, "description", "soap", new BigDecimal("2.123423"));

    assertEquals(product.getPriceUnit().toPlainString(), "2.12");
  }
}
