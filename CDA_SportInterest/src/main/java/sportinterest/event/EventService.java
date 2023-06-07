package sportinterest.event;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sportinterest.report.Report;



@Service
public class EventService {
	
	@Autowired
	EventRepository eventRepository;
	
/**
 * 
 * @return all events
 */
	
	public List<Event> getEvents() {
				
		return eventRepository.findAll();	
	}
	

/**
 * add one event	
 * @param event
 */
	public void addEvent(Event event) {

		eventRepository.save(event);
	}

/**
 * get one event by his id	
 * @param id
 * @return
 */
	public Optional<Event> getOneEvent(Integer id) {

		return eventRepository.findById(id);
	}

/**
 * get events by restricted type	
 * @param restricted
 * @param association id
 * @return
 */
	public List<Event> getEventsByRestrictedAndAssociationId(Boolean restricted, Integer id) {
	
		return eventRepository.findByRestrictedAndAssociationId(restricted, id);
	}

/**
 * update attributes of one event
 * @param id
 * @param event
 */
	public void updateEvent(Integer id, Event event) {

		eventRepository.save(event);
	}

/**
 * Delete one event by his id	
 * @param id
 */
	public void deleteEvent(Integer id) {

		eventRepository.deleteById(id);
	}
	
/**
 * Set report_id by event_id	
 * @param event_id
 * @param report
 */
	
	public void setReportById(int id, Report report) {
		try {
			Event event = eventRepository.getById(id);
			event.setReport(report);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

/**
 * get all events order by date ASC
 * @param Pageable limit display
 */
	public List<Event> getAllByOrderByDateAsc(Pageable pageable) {
		
		return eventRepository.findAllByOrderByDateAsc(pageable);
	
	}

/**
 * get all events by association id order by date ASC
 * @param Pageable limit display
 * @param association id
 */
	public List<Event> getAllByAssociationIdOrderByDateAsc(Integer id, Pageable unpaged) {


		return eventRepository.findAllByAssociationIdOrderByDateAsc(id, unpaged);
	}
	
/**
 * get all events by report_id isNull
 * @return
 */
	public List<Event> getAllEventByReportIdNull() {
		
		return eventRepository.findAllEventByReportIsNull();
	}
	
/**
 * get all events by report_id isNotNull
 * @return
 */
	public List<Event> getAllEventByReportIdNotNull() {
		
		return eventRepository.findAllEventByReportIsNotNull();
	}

/**
 * get all events by restricted is false
 * @return
 */	
	public List<Event> findAllByRestrictedFalse() {
		
		return eventRepository.findAllEventByRestrictedIsFalse();
}

	public List<Event> findAllByRestrictedFalseAndAssociationId(Integer id) {
		return eventRepository.findAllEventByRestrictedIsFalseAndAssociationId(id);
	}
}

