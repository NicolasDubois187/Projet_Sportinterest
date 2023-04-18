package sportinterest.event;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EventController {
	
	@Autowired
	EventService eventService;
	
/**
 * get all events	
 * @return
 */
    @GetMapping("events")
    public List<Event> getEvents(){
        return eventService.getEvents();
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
