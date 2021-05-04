package info.wallyson.tcsstore.persistence.product;

import info.wallyson.tcsstore.product.domain.Product;
import info.wallyson.tcsstore.product.ports.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
  private final ProductRepositoryJpaAdapter repositoryJpaAdapter;

  public ProductRepositoryImpl(ProductRepositoryJpaAdapter repositoryJpaAdapter) {
    this.repositoryJpaAdapter = repositoryJpaAdapter;
  }

  @Override
  public Optional<Product> findById(Long id) {
    var productEntity = repositoryJpaAdapter.findById(id);
    return productEntity.map(ProductEntity::toProduct);
  }

  @Override
  public boolean existsById(Long id) {
    return repositoryJpaAdapter.existsById(id);
  }

  @Override
  public Product save(Product product) {
    var entity = ProductEntity.fromProduct(product);
    var savedProduct = repositoryJpaAdapter.save(entity);
    return savedProduct.toProduct();
  }

  @Override
  public void updateProduct(Product clientNewData) {
    repositoryJpaAdapter
        .findById(clientNewData.getId())
        .ifPresent(
            productEntity -> {
              if (Objects.nonNull(clientNewData.getName()))
                productEntity.setName(clientNewData.getName());

              if (Objects.nonNull(clientNewData.getDescription()))
                productEntity.setDescription(clientNewData.getDescription());

              if (Objects.nonNull(clientNewData.getPriceUnit()))
                productEntity.setPriceUnit(clientNewData.getPriceUnit());

              repositoryJpaAdapter.save(productEntity);
            });
  }

  @Override
  public void deleteById(Long id) {
    repositoryJpaAdapter.deleteById(id);
  }
}
