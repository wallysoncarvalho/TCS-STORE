package info.wallyson.tcsstore.client.usecases;

import info.wallyson.tcsstore.client.exception.ClientDontExistException;
import info.wallyson.tcsstore.client.util.ClientCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

final class DeleteClientTests extends ClientTestBaseClass {
  private DeleteClient deleteClient;

  @BeforeEach
  void setUp() {
    this.deleteClient = new DeleteClient(clientRepository);
  }

  @Test
  @DisplayName("Should return exception when trying to delete client that dont exists")
  void shouldThrowExceptionWhenDeletingClientThatDontExists() {
    var client = ClientCreator.single();

    when(clientRepository.existsByCpf(client.getCpfString())).thenReturn(false);

    assertThrows(ClientDontExistException.class, () -> deleteClient.delete(client.getCpfString()));
    verify(clientRepository, times(1)).existsByCpf(client.getCpfString());
  }

  @Test
  @DisplayName("Should delete existing client")
  void shouldDeleteExistingClient() {
    var client = ClientCreator.single();
    var clientCPF = client.getCpfString();

    when(clientRepository.existsByCpf(clientCPF)).thenReturn(true);

    deleteClient.delete(clientCPF);

    verify(clientRepository, times(1)).existsByCpf(clientCPF);
    verify(clientRepository, times(1)).deleteById(clientCPF);
  }
}
