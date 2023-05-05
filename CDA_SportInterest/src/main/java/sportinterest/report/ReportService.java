package sportinterest.report;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sportinterest.event.Event;
import sportinterest.event.EventRepository;



@Service
public class ReportService {
	
	@Autowired
	ReportRepository reportRepository;
	
	@Autowired
	EventRepository eventRepository;
	
/**
 * 
 * @return all reports
 */
	public List<Report> getReports() {
					
		return reportRepository.findAll();
	}
/**
 * add one report	
 * @param report
 */
	public void addReport(Report report) {

		reportRepository.save(report);
		
	}
/**
 * get one report by his id	
 * @param id
 * @return
 */
	public Optional<Report> getOneReport(Integer id) {

		return reportRepository.findById(id);
	}
/**
 * update attributes of one report
 * @param id
 * @param report
 */
	public void updateReport(Integer id, Report report) {

		reportRepository.save(report);
	}
/**
 * Delete one report by his id	
 * @param id
 */
	public void deleteReport(Integer id) {

		reportRepository.deleteById(id);
	}	
/**
 * Link one event with his report	
 * @param event
 * @param report
 */		
	@Transactional
	public void createOrUpdateEventReport(Event event, Report report) {
	    Event existingEvent = eventRepository.findById(event.getId()).orElse(null);
	    if (existingEvent != null) {
	        Report existingReport = existingEvent.getReport();
	        if (existingReport != null) {
	            // Mise à jour du rapport existant
	            existingReport.setName(report.getName());
	            existingReport.setDescription(report.getDescription());
	            reportRepository.save(existingReport);
	        } else {
	            // Création d'un nouveau rapport
	            reportRepository.save(report);
	            existingEvent.setReport(report);
	            eventRepository.save(existingEvent);
	        }
	    }
	}
}
