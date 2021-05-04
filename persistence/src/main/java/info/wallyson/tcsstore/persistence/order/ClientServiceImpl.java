package info.wallyson.tcsstore.persistence.order;

import info.wallyson.tcsstore.client.ports.ClientRepository;
import info.wallyson.tcsstore.order.domain.ClientData;
import info.wallyson.tcsstore.order.ports.ClientService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientServiceImpl implements ClientService {
  private final ClientRepository clientRepository;

  public ClientServiceImpl(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  @Override
  public Optional<ClientData> getClientByCpf(String cpf) {
    return clientRepository
        .findById(cpf)
        .map(
            client ->
                new ClientData(
                    client.getCpfString(), client.getFullName(), client.getPostalCode()));
  }
}
