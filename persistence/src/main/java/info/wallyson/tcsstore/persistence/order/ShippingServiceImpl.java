package info.wallyson.tcsstore.persistence.order;

import info.wallyson.tcsstore.order.domain.ShippingData;
import info.wallyson.tcsstore.order.ports.ShippingService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class ShippingServiceImpl implements ShippingService {
  @Override
  public Optional<ShippingData> getByCEP(String CEP) {
    return Optional.of(new ShippingData("MG", "Minas Gerais", BigDecimal.TEN));
  }
}
