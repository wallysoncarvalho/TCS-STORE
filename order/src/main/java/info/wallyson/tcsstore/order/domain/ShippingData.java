package info.wallyson.tcsstore.order.domain;

import java.math.BigDecimal;

public class ShippingData {
  private final String UF;
  private final String district;
  private final BigDecimal cost;

  public ShippingData(String UF, String district, BigDecimal cost) {
    this.UF = UF;
    this.district = district;
    this.cost = cost;
  }

  public BigDecimal getCost() {
    return cost;
  }
}
