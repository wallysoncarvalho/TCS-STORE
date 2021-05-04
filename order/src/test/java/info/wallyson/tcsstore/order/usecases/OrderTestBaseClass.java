package info.wallyson.tcsstore.order.usecases;

import info.wallyson.tcsstore.order.ports.ClientService;
import info.wallyson.tcsstore.order.ports.OrderRepository;
import info.wallyson.tcsstore.order.ports.ProductService;
import info.wallyson.tcsstore.order.ports.ShippingService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
abstract class OrderTestBaseClass {
  @Mock OrderRepository orderRepository;
  @Mock ShippingService shippingService;
  @Mock ClientService clientService;
  @Mock ProductService productService;
}
