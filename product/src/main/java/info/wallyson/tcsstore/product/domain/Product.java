package info.wallyson.tcsstore.product.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {
  private final Long id;
  private final String name;
  private final String description;
  private final BigDecimal priceUnit;

  public Product(Long id, String description, String name, BigDecimal priceUnit) {
    this.id = id;
    this.description = description;
    this.name = name;
    this.priceUnit = priceUnit.setScale(2, RoundingMode.HALF_EVEN);
  }

  public Long getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public String getName() {
    return name;
  }

  public BigDecimal getPriceUnit() {
    return priceUnit;
  }
}
