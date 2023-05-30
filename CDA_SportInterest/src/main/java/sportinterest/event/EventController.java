package sportinterest.event;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EventController {
	
	@Autowired
	EventService eventService;
	
/**
 * get all events	
 * @return
 */
//    @GetMapping("events")
//    public List<Event> getEvents(){
//        return eventService.getEvents();
//    }
    
/**
 * get all events order by date ASC and optional limit
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
 * get all events by association id order by date ASC and optional limit
 * @return
 * @param limit
 * @param association id
 */	
	@GetMapping("events/association/{id}")
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
 * get all events by report_id isNull
 * @return
 */	
	@GetMapping("events/not-report")
	public List<Event> getEventsByReportIdIsNull() {
		return eventService.getAllEventByReportIdNull();
	}
	
/**
 * get all events by report_id isNotNull
 * @return
 */	
	@GetMapping("events/report-ok")
	public List<Event> getEventsByReportIdIsNotNull() {
		return eventService.getAllEventByReportIdNotNull();
	}
	
/**
 * get all events by restricted is false
 * @return
 */
	@GetMapping("/events/not-restricted")
	public List<Event> getEventsNotRestricted() {
	    return eventService.findAllByRestrictedFalse();
	}
	
/**
 * add one event
 * @param newEvent
 */
    @PostMapping("events")
    public void postEvent(@RequestBody Event newEvent){
		    
        eventService.addEvent(newEvent);
    }

/**
 * get one event by his id    
 * @param id
 * @return
 */
    @GetMapping("events/{id}")
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
* get one event by restricted type    
* @param restricted
* @param associationId
* @return
*/
    @GetMapping("restricted/{id}")
    public List<Event> getEventByRestrictedAndAssociationId(@RequestParam(value = "restricted", defaultValue = "false") Boolean restricted, @PathVariable("id") Integer id) {
        List<Event> events = eventService.getEventByRestrictedAndAssociationId(restricted, id);
        if (events.isEmpty()) {
           // /!\ CAS D'ERREUR //
        }
        return events;
    }

/**
 * update one event by his id    
 * @param id
 * @param event
 */
    @PutMapping("events/{id}")
    public void updateEvent(@PathVariable("id") Integer id, @RequestBody Event event) {
    	Optional<Event> oEvent = eventService.getOneEvent(id);
    	if(oEvent.isPresent()) {
		    		
    	eventService.updateEvent(id, event);
    	}
    }

/**
 * delete one event by his id    
 * @param id
 */
    @DeleteMapping("events/{id}")
    public void deleteEvent(@PathVariable("id") Integer id) {
    	eventService.deleteEvent(id);
    }
	
}
