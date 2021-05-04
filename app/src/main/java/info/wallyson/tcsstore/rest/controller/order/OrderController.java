package info.wallyson.tcsstore.rest.controller.order;

import info.wallyson.tcsstore.order.domain.Order;
import info.wallyson.tcsstore.order.domain.OrderReceipt;
import info.wallyson.tcsstore.order.usecases.CreateOrder;
import info.wallyson.tcsstore.order.usecases.GenerateOrderReceipt;
import info.wallyson.tcsstore.order.usecases.GetOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
  private final CreateOrder createOrder;
  private final GetOrder getOrder;
  private final GenerateOrderReceipt generateOrderReceipt;

  public OrderController(
      CreateOrder createOrder, GetOrder getOrder, GenerateOrderReceipt generateOrderReceipt) {
    this.createOrder = createOrder;
    this.getOrder = getOrder;
    this.generateOrderReceipt = generateOrderReceipt;
  }

  @PostMapping
  public ResponseEntity<Void> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
    var createOrderData = createOrderRequest.toOrderData();
    createOrder.create(createOrderData);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping("{orderId}")
  public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
    var order = getOrder.getOrder(orderId);
    return ResponseEntity.ok(order);
  }

  @GetMapping("{orderId}/receipt")
  public ResponseEntity<OrderReceipt> getOrderReceipt(@PathVariable Long orderId) {
    var orderReceipt = generateOrderReceipt.getReceipt(orderId);
    return ResponseEntity.ok(orderReceipt);
  }
}
