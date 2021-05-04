package info.wallyson.tcsstore.client.usecases;

import info.wallyson.tcsstore.client.ports.ClientRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
abstract class ClientTestBaseClass {
  @Mock ClientRepository clientRepository;
}
