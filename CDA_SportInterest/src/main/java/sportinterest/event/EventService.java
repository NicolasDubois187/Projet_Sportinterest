package sportinterest.event;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
		
}

