package info.wallyson.tcsstore.order.ports;

import info.wallyson.tcsstore.order.domain.ClientData;

import java.util.Optional;

public interface ClientService {
  Optional<ClientData> getClientByCpf(String cpf);
}
