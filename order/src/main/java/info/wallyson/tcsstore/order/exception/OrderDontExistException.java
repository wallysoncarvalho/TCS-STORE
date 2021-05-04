package info.wallyson.tcsstore.order.exception;

public class OrderDontExistException extends BaseOrderException{
    public OrderDontExistException(Long id) {
        super(String.format("Order with id '%s' doesnt exist.", id));
    }
}
