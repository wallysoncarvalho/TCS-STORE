package info.wallyson.tcsstore.order.domain;

public class ClientData {
  private final String cpf;
  private final String name;
  private final String CEP;

  public ClientData(String cpf, String name, String CEP) {
    this.cpf = cpf;
    this.name = name;
    this.CEP = CEP;
  }

  public String getCpf() {
    return cpf;
  }

  public String getName() {
    return name;
  }

  public String getCEP() {
    return CEP;
  }
}
