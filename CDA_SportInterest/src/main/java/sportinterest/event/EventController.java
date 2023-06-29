package sportinterest.event;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RequestMapping("event/")
@RestController
public class EventController {
	
	@Autowired
	EventService eventService;
    
	/**
	 * Get all events order by date ASC and optional limit
	 * @return
	 * @param limit
	 */
	@GetMapping("events")
	public List<Event> getEvents(@RequestParam(required = false) Integer limit) {
		if (limit == null) {
			return eventService.getAllByOrderByDateAsc(null);
		} else {
			Pageable pageable = PageRequest.of(0, limit, Sort.by("date").ascending());
			return eventService.getAllByOrderByDateAsc(pageable);
		}
	}
	
	/**
	 * Get all events by association id order by date ASC and optional limit
	 * @return
	 * @param limit
	 * @param id association
	 */
	@GetMapping("association/{id}")
	public List<Event> getEventsByAssociationId(
	        @PathVariable Integer id,
	        @RequestParam(required = false) Integer limit) {
	    if (limit == null) {
	        return eventService.getAllByAssociationIdOrderByDateAsc(id, Pageable.unpaged());
	    } else {
	        Pageable pageable = PageRequest.of(0, limit, Sort.by("date").ascending());
	        return eventService.getAllByAssociationIdOrderByDateAsc(id, pageable);
	    }
	}
	
	/**
	 * Get all events by report_id isNull
	 * @return
	 */
	@GetMapping("not-report")
	public List<Event> getEventsByReportIdIsNull() {
		return eventService.getAllEventByReportIdNull();
	}

	
	/**
	 * Get all events by report_id isNotNull
	 * @return
	 */
	@GetMapping("reported")
	public List<Event> getEventsByReportIdIsNotNull() {
		return eventService.getAllEventByReportIdNotNull();
	}
	
	/**
	 * Get all events by restricted is false
	 * @return
	 */
	@GetMapping("public")
	public List<Event> getEventsNotRestricted() {
	    return eventService.findAllByRestrictedFalse();
	}
	
	/**
	 * Add one event
	 * @param newEvent
	 */
    @PostMapping("create")
    public void postEvent(@RequestBody Event newEvent){
		    
        eventService.addEvent(newEvent);
    }

	/**
	 * Get one event by his id
	 * @param id
	 * @return
	 */
    @GetMapping("id/{id}")
    public ResponseEntity<Event> getOneEvent(@PathVariable("id") Integer id){
    	Optional<Event> oEvent = eventService.getOneEvent(id);
    	if(oEvent.isEmpty()) {
    		// 404
    		return ResponseEntity.notFound().build();
    	} else {
    		Event e = oEvent.get();
    		return ResponseEntity.ok(e);
    	}
    }

	/**
	 * update one event
	 * @param event
	 */
    @PutMapping("edit")
    public void updateEvent(@RequestBody Event event) {
    	Optional<Event> oEvent = eventService.getOneEvent(event.getId());
    	if(oEvent.isPresent()) {
    	eventService.updateEvent(event);
    	}
    }

/**
 * delete one event by his id    
 * @param id
 */
    @DeleteMapping("delete/{id}")
    public void deleteEvent(@PathVariable("id") Integer id) {
    	eventService.deleteEvent(id);
    }

}
