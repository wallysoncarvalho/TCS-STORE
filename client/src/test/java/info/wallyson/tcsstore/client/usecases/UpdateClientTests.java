package info.wallyson.tcsstore.client.usecases;

import info.wallyson.tcsstore.client.exception.ClientDontExistException;
import info.wallyson.tcsstore.client.util.ClientCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

final class UpdateClientTests extends ClientTestBaseClass {

  private UpdateClient updateClient;

  @BeforeEach
  void setUp() {
    updateClient = new UpdateClient(clientRepository);
  }

  @Test
  @DisplayName("Should return exception when trying to update client that dont exists")
  void shouldThrowExceptionWhenUpdatingClientThatDontExists() {
    var client = ClientCreator.single();

    when(clientRepository.existsByCpf(client.getCpfString())).thenReturn(false);

    assertThrows(ClientDontExistException.class, () -> updateClient.update(client));
    verify(clientRepository, times(1)).existsByCpf(client.getCpfString());
  }

  @Test
  @DisplayName("Should update client information")
  void shouldUpdateClientInformation() {
    var client = ClientCreator.single();

    when(clientRepository.existsByCpf(client.getCpfString())).thenReturn(true);

    updateClient.update(client);

    verify(clientRepository, times(1)).existsByCpf(client.getCpfString());
    verify(clientRepository, times(1)).updateClient(client);
  }
}
