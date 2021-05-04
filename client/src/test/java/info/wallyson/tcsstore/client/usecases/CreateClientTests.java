package info.wallyson.tcsstore.client.usecases;

import info.wallyson.tcsstore.client.exception.ClientExistsException;
import info.wallyson.tcsstore.client.util.ClientCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

final class CreateClientTests extends ClientTestBaseClass {
  private CreateClient createClient;

  @BeforeEach
  void setUp() {
    createClient = new CreateClient(clientRepository);
  }

  @Test
  @DisplayName("Should throw exception when client already exists")
  void shouldThrowExceptionWhenClientAlreadyExists() {
    var client = ClientCreator.single();

    when(clientRepository.existsByCpf(client.getCpfString())).thenReturn(true);

    assertThrows(ClientExistsException.class, () -> createClient.create(client));
    verify(clientRepository, times(1)).existsByCpf(client.getCpfString());
  }

  @Test
  @DisplayName("Should create a client")
  void shouldCreateClient() {
    var client = ClientCreator.single();

    when(clientRepository.existsByCpf(client.getCpfString())).thenReturn(false);

    createClient.create(client);
    verify(clientRepository, times(1)).existsByCpf(client.getCpfString());
    verify(clientRepository, times(1)).save(client);
  }
}
