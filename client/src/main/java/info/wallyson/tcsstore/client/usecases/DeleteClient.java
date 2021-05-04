package info.wallyson.tcsstore.client.usecases;

import info.wallyson.tcsstore.client.exception.ClientDontExistException;
import info.wallyson.tcsstore.client.ports.ClientRepository;

public class DeleteClient extends ClientBaseUseCase {
  public DeleteClient(ClientRepository clientRepository) {
    super(clientRepository);
  }

  public void delete(String cpf) {
    if (!clientRepository.existsByCpf(cpf)) throw new ClientDontExistException(cpf);
    clientRepository.deleteById(cpf);
    logger.info("Client with CPF {} was deleted", cpf);
  }
}
