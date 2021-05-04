package info.wallyson.tcsstore.client.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseClientException extends RuntimeException {
  final Logger logger = LoggerFactory.getLogger(BaseClientException.class);

  public BaseClientException(String message) {
    super(message);
    logger.error(message);
  }
}
