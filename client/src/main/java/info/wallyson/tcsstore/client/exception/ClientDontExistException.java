package info.wallyson.tcsstore.client.exception;

public class ClientDontExistException extends BaseClientException {
  public ClientDontExistException(String clientCpf) {
    super(String.format("Client with CPF '%s' do not exists.", clientCpf));
  }
}
