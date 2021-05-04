package info.wallyson.tcsstore.order.ports;

import info.wallyson.tcsstore.order.domain.ShippingData;

import java.util.Optional;

public interface ShippingService {
  Optional<ShippingData> getByCEP(String CEP);
}
