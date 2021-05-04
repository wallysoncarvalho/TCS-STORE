package info.wallyson.tcsstore.client.util;

import info.wallyson.tcsstore.client.domain.CPF;
import info.wallyson.tcsstore.client.domain.Client;

public class ClientCreator {

  public static Client single() {

    var cpf = new CPF("550.282.920-00");
    return new Client(cpf, "asdsa", "adasd", "asdasd", "asdasdas", "asdasdas", "asdasd");
  }
}
