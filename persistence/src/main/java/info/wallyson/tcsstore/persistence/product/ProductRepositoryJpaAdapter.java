package info.wallyson.tcsstore.persistence.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProductRepositoryJpaAdapter extends JpaRepository<ProductEntity, Long> {


}
