package info.wallyson.tcsstore.persistence.client;

import info.wallyson.tcsstore.client.domain.Client;
import info.wallyson.tcsstore.client.ports.ClientRepository;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
public class ClientRepositoryImpl implements ClientRepository {
  private final ClientRepositoryJpaAdapter clientRepositoryJpaAdapter;

  public ClientRepositoryImpl(ClientRepositoryJpaAdapter clientRepositoryJpaAdapter) {
    this.clientRepositoryJpaAdapter = clientRepositoryJpaAdapter;
  }

  @Override
  public Optional<Client> findById(String cpf) {
    var client = clientRepositoryJpaAdapter.findById(cpf);
    return client.map(ClientEntity::toClient);
  }

  @Override
  public boolean existsByCpf(String cpf) {
    return clientRepositoryJpaAdapter.existsById(cpf);
  }

  @Override
  public Client save(Client client) {
    var entity = ClientEntity.fromClient(client);
    var savedClient = clientRepositoryJpaAdapter.save(entity);
    return savedClient.toClient();
  }

  public void updateClient(Client clientNewData) {
    clientRepositoryJpaAdapter
        .findById(clientNewData.getCpfString())
        .ifPresent(
            clientSaved -> {
              if (Objects.nonNull(clientNewData.getCity()))
                clientSaved.setCity(clientNewData.getCity());

              if (Objects.nonNull(clientNewData.getDistrict()))
                clientSaved.setDistrict(clientNewData.getDistrict());

              if (Objects.nonNull(clientNewData.getFullAddress()))
                clientSaved.setFullAddress(clientNewData.getFullAddress());

              if (Objects.nonNull(clientNewData.getPhone()))
                clientSaved.setPhone(clientNewData.getPhone());

              if (Objects.nonNull(clientNewData.getPostalCode()))
                clientSaved.setPostalCode(clientNewData.getPostalCode());

              if (Objects.nonNull(clientNewData.getFullName()))
                clientSaved.setFullName(clientNewData.getFullName());

              clientRepositoryJpaAdapter.save(clientSaved);
            });
  }

  @Override
  public void deleteById(String cpf) {
    clientRepositoryJpaAdapter.deleteById(cpf);
  }
}
