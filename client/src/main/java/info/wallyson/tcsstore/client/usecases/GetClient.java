package info.wallyson.tcsstore.client.usecases;

import info.wallyson.tcsstore.client.domain.Client;
import info.wallyson.tcsstore.client.exception.ClientDontExistException;
import info.wallyson.tcsstore.client.ports.ClientRepository;

public class GetClient extends ClientBaseUseCase {

  public GetClient(ClientRepository clientRepository) {
    super(clientRepository);
  }

  public Client getClientByCpf(String cpf) {
    logger.info("Getting client with CPF {}", cpf);
    return clientRepository.findById(cpf).orElseThrow(() -> new ClientDontExistException(cpf));
  }
}
