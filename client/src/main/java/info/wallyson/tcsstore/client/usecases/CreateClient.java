package info.wallyson.tcsstore.client.usecases;

import info.wallyson.tcsstore.client.domain.Client;
import info.wallyson.tcsstore.client.exception.ClientExistsException;
import info.wallyson.tcsstore.client.ports.ClientRepository;

public class CreateClient extends ClientBaseUseCase {
  public CreateClient(ClientRepository clientRepository) {
    super(clientRepository);
  }

  public void create(Client client) {
    if (clientRepository.existsByCpf(client.getCpfString()))
      throw new ClientExistsException(client.getCpfString());

    clientRepository.save(client);

    logger.info("New client created with CPF {}", client.getCpfString());
  }
}
