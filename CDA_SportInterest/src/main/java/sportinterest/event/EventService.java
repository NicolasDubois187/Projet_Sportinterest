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
	 * Get all events
	 * @return
	 */
	public List<Event> getEvents() {
				
		return eventRepository.findAll();	
	}
	

	/**
	 * Add one event
	 * @param event
	 */
	public void addEvent(Event event) {

		eventRepository.save(event);
	}

	/**
	 * Get one event by his id
	 * @param id
	 * @return
	 */
	public Optional<Event> getOneEvent(Integer id) {

		return eventRepository.findById(id);
	}

	/**
	 * Get events by associationid and restricted type
	 * @param restricted
	 * @param association id
	 * @return
	 */
	public List<Event> getEventsByRestrictedAndAssociationId(Boolean restricted, Integer id) {
	
		return eventRepository.findByRestrictedAndAssociationId(restricted, id);
	}

	/**
	 * Update attributes of one event
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
	 * @param id event
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
	 * Get all events order by date ASC
	 * @param pageable limit display
	 */
	public List<Event> getAllByOrderByDateAsc(Pageable pageable) {
		
		return eventRepository.findAllByOrderByDateAsc(pageable);
	
	}

	/**
	 * Get all events by association id order by date ASC
	 * @param unpaged limit display
	 * @param id association
	 */
	public List<Event> getAllByAssociationIdOrderByDateAsc(Integer id, Pageable unpaged) {
		return eventRepository.findAllByAssociationIdOrderByDateAsc(id, unpaged);
	}
	
	/**
	 * Get all events by report_id isNull
	 * @return
	 */
	public List<Event> getAllEventByReportIdNull() {
		
		return eventRepository.findAllEventByReportIsNull();
	}
	
	/**
	 * Get all events which have a report
	 * @return
	 */
	public List<Event> getAllEventByReportIdNotNull() {
		
		return eventRepository.findAllEventByReportIsNotNull();
	}

	/**
	 * Get all public events
	 * @return
	 */
	public List<Event> findAllByRestrictedFalse() {
		return eventRepository.findAllEventByRestrictedIsFalse();
}

	/**
	 * Get all public events by association id
	 * @param id
	 * @return
	 */
	public List<Event> findAllByRestrictedFalseAndAssociationId(Integer id) {
		return eventRepository.findAllEventByRestrictedIsFalseAndAssociationId(id);
	}
}

