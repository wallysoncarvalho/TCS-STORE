package info.wallyson.tcsstore.order.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseOrderException extends RuntimeException {
  final Logger logger = LoggerFactory.getLogger(BaseOrderException.class);

  public BaseOrderException(String message) {
    super(message);
    logger.error(message);
  }
}
