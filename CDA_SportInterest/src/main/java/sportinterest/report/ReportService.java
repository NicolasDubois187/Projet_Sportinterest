package sportinterest.report;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sportinterest.event.EventRepository;



@Service
public class ReportService {
	
	@Autowired
	ReportRepository reportRepository;

	/**
	 * Get all reports
	 * @return
	 */
	
	public List<Report> getReports() {
					
		return reportRepository.findAll();
	}

	/**
	 * Add one report
	 * @param report
	 */
	public void addReport(Report report) {

		reportRepository.save(report);
		
	}

	/**
	 * Get one report by his id
	 * @param id
	 * @return
	 */
	public Optional<Report> getOneReport(Integer id) {

		return reportRepository.findById(id);
	}

	/**
	 * Update attributes of one report
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
}
