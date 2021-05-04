package info.wallyson.tcsstore.rest.exception;

import info.wallyson.tcsstore.client.exception.BaseClientException;
import info.wallyson.tcsstore.order.exception.BaseOrderException;
import info.wallyson.tcsstore.product.exception.ProductBaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({
    BaseClientException.class,
    ProductBaseException.class,
    BaseOrderException.class
  })
  protected ResponseEntity<Object> handleDomainException(Exception exception) {
    var errorMessage = new ErrorResponse(exception.getMessage());
    return ResponseEntity.badRequest().body(errorMessage);
  }
}
