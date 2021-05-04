package info.wallyson.tcsstore.order.usecases;

import info.wallyson.tcsstore.order.exception.ClientNotFoundException;
import info.wallyson.tcsstore.order.exception.ProductNotFoundException;
import info.wallyson.tcsstore.order.exception.ShippingDestinationException;
import info.wallyson.tcsstore.order.util.OrderDataCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

final class CreateOrderTests extends OrderTestBaseClass {
  private CreateOrder createOrder;

  @BeforeEach
  void setUp() {
    this.createOrder =
        new CreateOrder(orderRepository, shippingService, clientService, productService);
  }

  @Test
  @DisplayName("Should throw exception when order client do not exists")
  void shouldThrowExceptionWhenOrderClientDontExists() {
    var createOrderData = OrderDataCreator.getCreateOrderData();

    when(clientService.getClientByCpf(createOrderData.getClientCpf())).thenReturn(Optional.empty());

    assertThrows(ClientNotFoundException.class, () -> createOrder.create(createOrderData));
    verify(clientService, times(1)).getClientByCpf(createOrderData.getClientCpf());
  }

  @Test
  @DisplayName("Should throw exception when shipping destination is not found")
  void shouldThrowExceptionWhenShippingDestinationCantBeFound() {
    var createOrderData = OrderDataCreator.getCreateOrderData();
    var client = OrderDataCreator.getClientData();

    when(clientService.getClientByCpf(createOrderData.getClientCpf()))
        .thenReturn(Optional.of(client));
    when(shippingService.getByCEP(client.getCEP())).thenReturn(Optional.empty());

    assertThrows(ShippingDestinationException.class, () -> createOrder.create(createOrderData));
    verify(clientService, times(1)).getClientByCpf(createOrderData.getClientCpf());
    verify(shippingService, times(1)).getByCEP(client.getCEP());
  }

  @Test
  @DisplayName("Should throw exception when order product do not exists")
  void shouldThrowExceptionWhenOrderProductDontExists() {
    var createOrderData = OrderDataCreator.getCreateOrderData();
    var client = OrderDataCreator.getClientData();
    var shipping = OrderDataCreator.getShippingData();

    when(clientService.getClientByCpf(createOrderData.getClientCpf()))
        .thenReturn(Optional.of(client));
    when(shippingService.getByCEP(client.getCEP())).thenReturn(Optional.of(shipping));
    when(productService.getProductById(anyLong())).thenReturn(Optional.empty());

    assertThrows(ProductNotFoundException.class, () -> createOrder.create(createOrderData));
    verify(clientService, times(1)).getClientByCpf(createOrderData.getClientCpf());
    verify(shippingService, times(1)).getByCEP(client.getCEP());
    verify(productService, times(1)).getProductById(anyLong());
  }

  @Test
  @DisplayName("Should create a new order")
  void shouldCreateOrder() {
    var createOrderData = OrderDataCreator.getCreateOrderData();
    var client = OrderDataCreator.getClientData();
    var shipping = OrderDataCreator.getShippingData();

    when(clientService.getClientByCpf(createOrderData.getClientCpf()))
        .thenReturn(Optional.of(client));
    when(shippingService.getByCEP(client.getCEP())).thenReturn(Optional.of(shipping));

    var products = OrderDataCreator.getProductDataList();

    when(productService.getProductById(1L)).thenReturn(Optional.of(products.get(0)));
    when(productService.getProductById(2L)).thenReturn(Optional.of(products.get(1)));

    createOrder.create(createOrderData);

    verify(orderRepository, times(1)).save(any());
    verify(clientService, times(1)).getClientByCpf(createOrderData.getClientCpf());
    verify(shippingService, times(1)).getByCEP(client.getCEP());
    verify(productService, times(createOrderData.getOrderItemsData().size()))
        .getProductById(anyLong());
  }
}
