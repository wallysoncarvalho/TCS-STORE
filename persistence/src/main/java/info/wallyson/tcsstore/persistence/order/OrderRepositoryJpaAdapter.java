package info.wallyson.tcsstore.persistence.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepositoryJpaAdapter extends JpaRepository<OrderEntity, Long> {}
