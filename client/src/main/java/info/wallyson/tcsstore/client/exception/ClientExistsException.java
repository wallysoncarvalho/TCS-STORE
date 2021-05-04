package info.wallyson.tcsstore.client.exception;

public class ClientExistsException extends BaseClientException {
  public ClientExistsException(String clientCpf) {
    super(String.format("Client with '%s' already exists.", clientCpf));
  }
}
