package info.wallyson.tcsstore.client.usecases;

import info.wallyson.tcsstore.client.domain.Client;
import info.wallyson.tcsstore.client.exception.ClientDontExistException;
import info.wallyson.tcsstore.client.ports.ClientRepository;

public class UpdateClient extends ClientBaseUseCase {
  public UpdateClient(ClientRepository clientRepository) {
    super(clientRepository);
  }

  public void update(Client clientNewData) {
    if (!clientRepository.existsByCpf(clientNewData.getCpfString()))
      throw new ClientDontExistException(clientNewData.getCpfString());

    clientRepository.updateClient(clientNewData);

    logger.info("Client with CPF {} was updated.", clientNewData.getCpf().getCpf());
  }
}
