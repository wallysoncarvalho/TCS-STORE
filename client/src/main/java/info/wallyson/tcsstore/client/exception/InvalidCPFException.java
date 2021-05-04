package info.wallyson.tcsstore.client.exception;

public class InvalidCPFException extends BaseClientException {
  public InvalidCPFException(String cpf) {
    super(String.format("Invalid CPF '%s'.", cpf));
  }
}
