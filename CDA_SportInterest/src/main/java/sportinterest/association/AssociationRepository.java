package sportinterest.association;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociationRepository extends JpaRepository<Association, Integer> {

    Optional<Association> findByPresidentId(Integer id);
}
