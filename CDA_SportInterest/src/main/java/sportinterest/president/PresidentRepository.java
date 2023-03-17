package sportinterest.president;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresidentRepository extends JpaRepository<President, Integer>{

}
