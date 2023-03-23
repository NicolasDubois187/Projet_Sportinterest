package sportinterest.report;

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
public class ReportController {

	@Autowired
	ReportService reportService;
	
/**
 * get all reports	
 * @return
 */
    @GetMapping("reports")
    public List<Report> getReports(){
        return reportService.getReports();
    }
/**
 * add one report
 * @param newReport
 */
    @PostMapping("reports")
    public void postReport(@RequestBody Report newReport){
			    
        reportService.addReport(newReport);
    }
/**
 * get one report by his id    
 * @param id
 * @return
 */
    @GetMapping("reports/{id}")
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
 * update one report by his id    
 * @param id
 * @param report
 */
    @PutMapping("reports/{id}")
    public void updateReport(@PathVariable("id") Integer id, @RequestBody Report report) {
    	Optional<Report> oReport = reportService.getOneReport(id);
    	if(oReport.isPresent()) {
			    		
    	reportService.updateReport(id, report);
    	}
    }
/**
 * delete one report by his id    
 * @param id
 */
    @DeleteMapping("reports/{id}")
    public void deleteReport(@PathVariable("id") Integer id) {
    	reportService.deleteReport(id);
    }
}
