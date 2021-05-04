package info.wallyson.tcsstore.product.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ProductBaseException extends RuntimeException {
  final Logger logger = LoggerFactory.getLogger(ProductBaseException.class);

  public ProductBaseException(String message) {
    super(message);
    logger.error(message);
  }
}
