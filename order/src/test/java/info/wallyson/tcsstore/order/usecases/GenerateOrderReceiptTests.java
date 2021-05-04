package info.wallyson.tcsstore.order.usecases;

import info.wallyson.tcsstore.order.exception.ClientNotFoundException;
import info.wallyson.tcsstore.order.exception.OrderDontExistException;
import info.wallyson.tcsstore.order.exception.ShippingDestinationException;
import info.wallyson.tcsstore.order.util.OrderCreator;
import info.wallyson.tcsstore.order.util.OrderDataCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class GenerateOrderReceiptTests extends OrderTestBaseClass {
  private GenerateOrderReceipt generateOrderReceipt;

  @BeforeEach
  void setUp() {
    this.generateOrderReceipt =
        new GenerateOrderReceipt(orderRepository, shippingService, clientService);
  }

  @Test
  @DisplayName("Should throw exception when order do not exists.")
  void shouldThrowExceptionWhenOrderDontExists() {
    var orderId = 1L;

    when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

    assertThrows(OrderDontExistException.class, () -> generateOrderReceipt.getReceipt(orderId));
    verify(orderRepository, times(1)).findById(orderId);
  }

  @Test
  @DisplayName("Should throw exception when client cant be found")
  void shouldThrowExceptionWhenClientCantBeFound() {
    var order = OrderCreator.create();
    var orderId = order.getOrderId();

    when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
    when(clientService.getClientByCpf(order.getClient())).thenReturn(Optional.empty());

    assertThrows(ClientNotFoundException.class, () -> generateOrderReceipt.getReceipt(orderId));

    verify(orderRepository, times(1)).findById(orderId);
    verify(clientService, times(1)).getClientByCpf(order.getClient());
  }

  @Test
  @DisplayName("Should throw exception when shipping destination cant be found")
  void shouldThrowExceptionWhenShippingDestinationCantBeFound() {
    var order = OrderCreator.create();
    var client = OrderDataCreator.getClientData();

    when(orderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));
    when(clientService.getClientByCpf(order.getClient())).thenReturn(Optional.of(client));
    when(shippingService.getByCEP(client.getCEP())).thenReturn(Optional.empty());

    assertThrows(
        ShippingDestinationException.class,
        () -> generateOrderReceipt.getReceipt(order.getOrderId()));

    verify(orderRepository, times(1)).findById(order.getOrderId());
    verify(clientService, times(1)).getClientByCpf(order.getClient());
    verify(shippingService, times(1)).getByCEP(client.getCEP());
  }

  @Test
  @DisplayName("Should generate order receipt")
  void shouldGenerateOrderReceipt() {
    var order = OrderCreator.create();
    var client = OrderDataCreator.getClientData();
    var shipping = OrderDataCreator.getShippingData();

    when(orderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));
    when(clientService.getClientByCpf(order.getClient())).thenReturn(Optional.of(client));
    when(shippingService.getByCEP(client.getCEP())).thenReturn(Optional.of(shipping));

    var orderReceipt = generateOrderReceipt.getReceipt(order.getOrderId());

    assertEquals(order.getOrderItems().size(), orderReceipt.getProducts().size());
    assertEquals(client, orderReceipt.getClient());
    assertEquals(shipping, orderReceipt.getShipping());

    verify(orderRepository, times(1)).findById(order.getOrderId());
    verify(clientService, times(1)).getClientByCpf(order.getClient());
    verify(shippingService, times(1)).getByCEP(client.getCEP());
  }
}
