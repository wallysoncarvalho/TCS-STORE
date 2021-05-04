package info.wallyson.tcsstore.rest.util;

import info.wallyson.tcsstore.rest.controller.product.ProductRequest;

import java.math.BigDecimal;

public class ProductRequestCreator {

  public static ProductRequest create() {
    var productRequest = new ProductRequest("this is a food", "product 1", new BigDecimal("10.00"));
    productRequest.setId(1L);
    return productRequest;
  }
}
