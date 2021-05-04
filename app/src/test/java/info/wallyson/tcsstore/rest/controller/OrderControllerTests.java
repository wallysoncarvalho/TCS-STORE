package info.wallyson.tcsstore.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.wallyson.tcsstore.order.ports.ClientService;
import info.wallyson.tcsstore.order.ports.OrderRepository;
import info.wallyson.tcsstore.order.ports.ProductService;
import info.wallyson.tcsstore.order.ports.ShippingService;
import info.wallyson.tcsstore.rest.config.OrderBeans;
import info.wallyson.tcsstore.rest.controller.order.OrderController;
import info.wallyson.tcsstore.rest.exception.RestExceptionHandler;
import info.wallyson.tcsstore.rest.util.CreateOrderRequestCreator;
import info.wallyson.tcsstore.rest.util.OrderCreator;
import info.wallyson.tcsstore.rest.util.OrderDataCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(
    classes = {OrderController.class, OrderBeans.class, RestExceptionHandler.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTests {
  private final ObjectMapper mapper = new ObjectMapper();
  @Autowired private MockMvc mockMvc;
  @MockBean private OrderRepository orderRepository;
  @MockBean private ShippingService shippingService;
  @MockBean private ClientService clientService;
  @MockBean private ProductService productService;
  private final String BASE_URL = "/api/v1/order";

  @Test
  @DisplayName("Should request the creation of an order")
  void shouldRequestOrderCreation() throws Exception {
    var orderRequest = CreateOrderRequestCreator.create();

    var client = OrderDataCreator.getClientData();
    var shipping = OrderDataCreator.getShippingData();

    when(clientService.getClientByCpf(orderRequest.toOrderData().getClientCpf()))
        .thenReturn(Optional.of(client));
    when(shippingService.getByCEP(client.getCEP())).thenReturn(Optional.of(shipping));

    var products = OrderDataCreator.getProductDataList();
    when(productService.getProductById(1L)).thenReturn(Optional.of(products.get(0)));
    when(productService.getProductById(2L)).thenReturn(Optional.of(products.get(1)));

    this.mockMvc
        .perform(
            post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("utf-8")
                .content(mapper.writeValueAsString(orderRequest)))
        .andDo(print())
        .andExpect(status().isCreated());
  }

  @Test
  @DisplayName("Should get an order by its ID")
  void shouldGetAnOrderByID() throws Exception {
    var order = OrderCreator.create();

    when(orderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));

    this.mockMvc
        .perform(get(BASE_URL + "/{id}", order.getOrderId()))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  @DisplayName("Should get an order receipt")
  void shouldGetOrderReceipt() throws Exception {
    var order = OrderCreator.create();
    var client = OrderDataCreator.getClientData();
    var shipping = OrderDataCreator.getShippingData();

    when(orderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));
    when(clientService.getClientByCpf(order.getClient())).thenReturn(Optional.of(client));
    when(shippingService.getByCEP(client.getCEP())).thenReturn(Optional.of(shipping));

    this.mockMvc
        .perform(get(BASE_URL + "/{id}/receipt", order.getOrderId()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.client.cpf").value(client.getCpf()))
        .andExpect(jsonPath("$.client.name").value(client.getName()))
        .andExpect(jsonPath("$.client.cep").value(client.getCEP()))
        .andExpect(jsonPath("$.products").isArray())
        .andExpect(jsonPath("$.shipping.cost").isNumber())
        .andDo(print());
  }
}
