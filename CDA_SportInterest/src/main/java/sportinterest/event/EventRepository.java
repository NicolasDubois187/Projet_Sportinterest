package sportinterest.event;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer>{
	
	List<Event> findByRestrictedAndAssociationId(Boolean restricted, Integer id);
	
	List<Event> findAllByOrderByDateAsc(Pageable pageable);
	
	List<Event> findAllByAssociationIdOrderByDateAsc(Integer associationId, Pageable pageable);
	
	List<Event> findAllEventByReportIsNull();

	List<Event> findAllEventByReportIsNotNull();
	
	List<Event> findAllEventByRestrictedIsFalse();

}
