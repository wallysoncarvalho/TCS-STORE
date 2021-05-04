package info.wallyson.tcsstore.order.usecases;

import info.wallyson.tcsstore.order.domain.*;
import info.wallyson.tcsstore.order.exception.ClientNotFoundException;
import info.wallyson.tcsstore.order.exception.ProductNotFoundException;
import info.wallyson.tcsstore.order.exception.ShippingDestinationException;
import info.wallyson.tcsstore.order.ports.ClientService;
import info.wallyson.tcsstore.order.ports.OrderRepository;
import info.wallyson.tcsstore.order.ports.ProductService;
import info.wallyson.tcsstore.order.ports.ShippingService;

import java.util.Set;
import java.util.stream.Collectors;

public class CreateOrder extends OrderBaseUseCase {
  private final ShippingService shippingService;
  private final ClientService clientService;
  private final ProductService productService;

  public CreateOrder(
      OrderRepository orderRepository,
      ShippingService shippingService,
      ClientService clientService,
      ProductService productService) {
    super(orderRepository);
    this.shippingService = shippingService;
    this.clientService = clientService;
    this.productService = productService;
  }

  public void create(CreateOrderData createOrderData) {
    var client = getClient(createOrderData.getClientCpf());
    var shipping = getShippingData(client.getCEP());
    var orderItems =
        getProductsList(createOrderData.getOrderItemsData()).stream()
            .map(ProductData::toOrderItem)
            .collect(Collectors.toSet());

    var order = new Order(null, client.getCpf(), orderItems, shipping.getCost());

    orderRepository.save(order);

    logger.info("New order created for the client of CPF {}", createOrderData.getClientCpf());
  }

  private ClientData getClient(String cpf) {
    return clientService.getClientByCpf(cpf).orElseThrow(() -> new ClientNotFoundException(cpf));
  }

  private Set<ProductData> getProductsList(
      Set<CreateOrderData.CreateOrderItemData> createOrderItemDataSet) {
    return createOrderItemDataSet.stream()
        .map(
            data -> {
              var productId = data.getProductId();
              var product =
                  productService
                      .getProductById(productId)
                      .orElseThrow(() -> new ProductNotFoundException(productId));
              product.setAmount(data.getAmount());
              return product;
            })
        .collect(Collectors.toSet());
  }

  private ShippingData getShippingData(String cep) {
    return shippingService.getByCEP(cep).orElseThrow(() -> new ShippingDestinationException(cep));
  }
}
