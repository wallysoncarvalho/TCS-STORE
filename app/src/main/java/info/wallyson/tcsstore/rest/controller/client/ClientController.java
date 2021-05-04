package info.wallyson.tcsstore.rest.controller.client;

import info.wallyson.tcsstore.client.usecases.CreateClient;
import info.wallyson.tcsstore.client.usecases.DeleteClient;
import info.wallyson.tcsstore.client.usecases.GetClient;
import info.wallyson.tcsstore.client.usecases.UpdateClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {
  private final CreateClient createClient;
  private final GetClient getClient;
  private final UpdateClient updateClient;
  private final DeleteClient deleteClient;

  public ClientController(
      CreateClient createClient,
      GetClient getClient,
      UpdateClient updateClient,
      DeleteClient deleteClient) {
    this.createClient = createClient;
    this.getClient = getClient;
    this.updateClient = updateClient;
    this.deleteClient = deleteClient;
  }

  @PostMapping
  public ResponseEntity<Void> createClient(@RequestBody ClientRequest clientRequest) {
    createClient.create(clientRequest.toClient());
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping("{cpf}")
  public ResponseEntity<ClientRequest> getClient(@PathVariable String cpf) {
    var client = getClient.getClientByCpf(cpf);
    return ResponseEntity.ok(ClientRequest.fromClient(client));
  }

  @PutMapping("{cpf}")
  public ResponseEntity<Void> updateClient(
      @RequestBody UpdateClientRequest clientRequest, @PathVariable String cpf) {
    updateClient.update(clientRequest.toClient(cpf));
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("{cpf}")
  public ResponseEntity<Void> deleteClient(@PathVariable String cpf) {
    deleteClient.delete(cpf);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
