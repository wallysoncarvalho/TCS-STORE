package info.wallyson.tcsstore.rest.controller.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import info.wallyson.tcsstore.product.domain.Product;

import java.math.BigDecimal;

public class ProductRequest {
  private Long id;
  private String description;
  private String name;
  private BigDecimal priceUnit;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public ProductRequest(
      @JsonProperty(value = "description") String description,
      @JsonProperty(value = "category") String name,
      @JsonProperty(value = "priceUnit") BigDecimal priceUnit) {
    this.description = description;
    this.name = name;
    this.priceUnit = priceUnit;
  }

  public Product toProduct() {
    return new Product(this.id, this.description, this.name, this.priceUnit);
  }

  public static ProductRequest fromProduct(Product product) {
    return new ProductRequest(product.getDescription(), product.getName(), product.getPriceUnit());
  }

  public void setId(Long id) {
    this.id = id;
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
