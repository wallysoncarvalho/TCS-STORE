package info.wallyson.tcsstore.rest.config;

import info.wallyson.tcsstore.client.ports.ClientRepository;
import info.wallyson.tcsstore.client.usecases.CreateClient;
import info.wallyson.tcsstore.client.usecases.DeleteClient;
import info.wallyson.tcsstore.client.usecases.GetClient;
import info.wallyson.tcsstore.client.usecases.UpdateClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientBeans {

  private final ClientRepository clientRepository;

  public ClientBeans(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  @Bean
  public CreateClient createClientBean() {
    return new CreateClient(clientRepository);
  }

  @Bean
  public GetClient getClientBean() {
    return new GetClient(clientRepository);
  }

  @Bean
  public UpdateClient updateClientBean() {
    return new UpdateClient(clientRepository);
  }

  @Bean
  public DeleteClient deleteClientBean() {
    return new DeleteClient(clientRepository);
  }
}
