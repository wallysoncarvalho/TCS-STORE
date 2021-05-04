package info.wallyson.tcsstore.client.usecases;

import info.wallyson.tcsstore.client.ports.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class ClientBaseUseCase {
  final Logger logger = LoggerFactory.getLogger(ClientBaseUseCase.class);

  final ClientRepository clientRepository;

  public ClientBaseUseCase(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }
}
