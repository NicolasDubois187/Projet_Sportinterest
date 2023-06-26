package sportinterest.report;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sportinterest.event.EventService;


@RequestMapping("report/")
@RestController
public class ReportController {

	@Autowired
	ReportService reportService;
	
	@Autowired
	EventService eventService;
		
	
    /**
     * Get all reports
     * @return
     */
    @GetMapping("reports")
    public List<Report> getReports(){
        return reportService.getReports();
    }

    /**
     * Add one report
     * @param newReport
     */
    @PostMapping("create/event/{id}") //Id de l' event
    public void postReport(@PathVariable("id") Integer id, @RequestBody Report newReport){
		
    	eventService.setReportById(id, newReport);
        reportService.addReport(newReport);
        
    }

    /**
     * Get one report by his id
     * @param id
     * @return
     */
    @GetMapping("id/{id}")
    public ResponseEntity<Report> getOneReport(@PathVariable("id") Integer id){
    	Optional<Report> oReport = reportService.getOneReport(id);
    	if(oReport.isEmpty()) {
    		// 404
    		return ResponseEntity.notFound().build();
    	} else {
    		Report r = oReport.get();
    		return ResponseEntity.ok(r);
    	}
    }

    /**
     * Update one report by his id
     * @param id
     * @param report
     */
    @PutMapping("edit/{id}")
    public void updateReport(@PathVariable("id") Integer id, @RequestBody Report report) {
    	Optional<Report> oReport = reportService.getOneReport(id);
    	if(oReport.isPresent()) {
			    		
    	reportService.updateReport(id, report);
    	}
    }

    /**
     * Delete one report by his id
     * @param id
     */
    @DeleteMapping("delete/{id}")
    public void deleteReport(@PathVariable("id") Integer id) {
    	reportService.deleteReport(id);
    }
}
