package info.wallyson.tcsstore.product.util;

import info.wallyson.tcsstore.product.domain.Product;

import java.math.BigDecimal;

public class ProductCreator {

  public static Product create() {
    return new Product(1L, "description", "soap", new BigDecimal("2.123423"));
  }
}
