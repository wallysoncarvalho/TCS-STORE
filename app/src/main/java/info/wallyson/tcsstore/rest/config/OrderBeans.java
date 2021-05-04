package info.wallyson.tcsstore.rest.config;

import info.wallyson.tcsstore.order.ports.ClientService;
import info.wallyson.tcsstore.order.ports.OrderRepository;
import info.wallyson.tcsstore.order.ports.ProductService;
import info.wallyson.tcsstore.order.ports.ShippingService;
import info.wallyson.tcsstore.order.usecases.CreateOrder;
import info.wallyson.tcsstore.order.usecases.GenerateOrderReceipt;
import info.wallyson.tcsstore.order.usecases.GetOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderBeans {
  private final OrderRepository orderRepository;
  private final ShippingService shippingService;
  private final ClientService clientService;
  private final ProductService productService;

  public OrderBeans(
      OrderRepository orderRepository,
      ShippingService shippingService,
      ClientService clientService,
      ProductService productService) {
    this.orderRepository = orderRepository;
    this.shippingService = shippingService;
    this.clientService = clientService;
    this.productService = productService;
  }

  @Bean
  public CreateOrder createOrderBean() {
    return new CreateOrder(orderRepository, shippingService, clientService, productService);
  }

  @Bean
  public GetOrder getOrderUseCaseBean() {
    return new GetOrder(orderRepository);
  }

  @Bean
  public GenerateOrderReceipt generateOrderReceiptBean() {
    return new GenerateOrderReceipt(orderRepository, shippingService, clientService);
  }
}
