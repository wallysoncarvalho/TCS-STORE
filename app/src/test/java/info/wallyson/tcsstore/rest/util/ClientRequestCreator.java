package info.wallyson.tcsstore.rest.util;

import info.wallyson.tcsstore.rest.controller.client.ClientRequest;

public class ClientRequestCreator {

  public static ClientRequest create() {
    return new ClientRequest(
        "153.759.700-07",
        "tiringa da silva sauro",
        "3333222111",
        "rua leitao das neves",
        "222332424",
        "MGG",
        "teste");
  }
}
