package info.wallyson.tcsstore.client.ports;

import info.wallyson.tcsstore.client.domain.Client;

import java.util.Optional;

public interface ClientRepository {
  Optional<Client> findById(String cpf);

  boolean existsByCpf(String cpf);

  Client save(Client client);

  void updateClient(Client clientNewData);

  void deleteById(String cpf);
}
