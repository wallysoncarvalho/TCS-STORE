package info.wallyson.tcsstore.order.usecases;

import info.wallyson.tcsstore.order.domain.OrderReceipt;
import info.wallyson.tcsstore.order.exception.ClientNotFoundException;
import info.wallyson.tcsstore.order.exception.OrderDontExistException;
import info.wallyson.tcsstore.order.exception.ShippingDestinationException;
import info.wallyson.tcsstore.order.ports.ClientService;
import info.wallyson.tcsstore.order.ports.OrderRepository;
import info.wallyson.tcsstore.order.ports.ShippingService;

public class GenerateOrderReceipt extends OrderBaseUseCase {
  private final ShippingService shippingService;
  private final ClientService clientService;

  public GenerateOrderReceipt(
      OrderRepository orderRepository,
      ShippingService shippingService,
      ClientService clientService) {
    super(orderRepository);
    this.shippingService = shippingService;
    this.clientService = clientService;
  }

  public OrderReceipt getReceipt(Long orderId) {
    var order =
        orderRepository.findById(orderId).orElseThrow(() -> new OrderDontExistException(orderId));

    var clientCpf = order.getClient();

    var client =
        clientService
            .getClientByCpf(clientCpf)
            .orElseThrow(() -> new ClientNotFoundException(clientCpf));

    var shipping =
        shippingService
            .getByCEP(client.getCEP())
            .orElseThrow(() -> new ShippingDestinationException(client.getCEP()));

    logger.info("Generated receipt for order of ID {}", orderId);

    return new OrderReceipt(client, order.getOrderItems(), shipping);
  }
}
