package sportinterest.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
	
	@Autowired
	EventRepository eventRepository;
}
