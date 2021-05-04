package info.wallyson.tcsstore.order.exception;

public class ClientNotFoundException extends BaseOrderException {
  public ClientNotFoundException(String cpf) {
    super(String.format("Client with ID '%s' was not found.", cpf));
  }
}
