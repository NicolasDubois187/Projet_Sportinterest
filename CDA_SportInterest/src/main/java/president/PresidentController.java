package president;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PresidentController {

	@Autowired
	PresidentService presidentService;
}
