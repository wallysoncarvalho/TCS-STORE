package info.wallyson.tcsstore.persistence.client;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ClientRepositoryJpaAdapter extends JpaRepository<ClientEntity, String> {

}
