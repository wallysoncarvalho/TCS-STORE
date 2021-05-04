package info.wallyson.tcsstore.persistence.product;

import info.wallyson.tcsstore.product.domain.Product;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class ProductEntity {
  @Id @GeneratedValue private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "priceUnit")
  private BigDecimal priceUnit;

  public Product toProduct() {
    return new Product(this.id, this.description, this.name, this.priceUnit);
  }

  public static ProductEntity fromProduct(Product product) {
    var productEntity = new ProductEntity();
    productEntity.setName(product.getName());
    productEntity.setDescription(product.getDescription());
    productEntity.setPriceUnit(product.getPriceUnit());
    return productEntity;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPriceUnit(BigDecimal priceUnit) {
    this.priceUnit = priceUnit;
  }
}
