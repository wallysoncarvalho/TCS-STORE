package info.wallyson.tcsstore.product.exception;

import java.math.BigDecimal;

public class InvalidProductPriceException extends ProductBaseException {
  public InvalidProductPriceException(BigDecimal priceUnit) {
    super(
        String.format(
            "Product price should be greater than zero. Provided: '%s'",
            priceUnit.toPlainString()));
  }
}
