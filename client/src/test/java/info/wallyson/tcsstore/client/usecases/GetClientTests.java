package info.wallyson.tcsstore.client.usecases;

import info.wallyson.tcsstore.client.exception.ClientDontExistException;
import info.wallyson.tcsstore.client.util.ClientCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

final class GetClientTests extends ClientTestBaseClass {
  private GetClient getClient;

  @BeforeEach
  void setUp() {
    this.getClient = new GetClient(clientRepository);
  }

  @Test
  @DisplayName("Should throw exception when client do not exists")
  void shouldReturnExceptionForClientNotFound() {
    var client = ClientCreator.single();
    var clientCPF = client.getCpfString();

    when(clientRepository.findById(clientCPF)).thenReturn(Optional.empty());

    assertThrows(ClientDontExistException.class, () -> getClient.getClientByCpf(clientCPF));
    verify(clientRepository, times(1)).findById(clientCPF);
  }

  @Test
  @DisplayName("Should return existing client")
  void shouldReturnExistingClient() {
    var client = ClientCreator.single();
    var clientCPF = client.getCpfString();

    when(clientRepository.findById(client.getCpfString())).thenReturn(Optional.of(client));

    var savedClient = getClient.getClientByCpf(client.getCpfString());

    assertEquals(clientCPF, savedClient.getCpfString());
    verify(clientRepository, times(1)).findById(clientCPF);
  }
}
