package sportinterest.event;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import sportinterest.association.Association;
import sportinterest.report.Report;



@Entity
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private Date date;
	private String adress;
	@ManyToOne
	private Association association;
    @OneToOne
	private Report report;
    
	public Event() {
		
	}

	public Event(int id, String name, String description, Date date, String adress, Association association) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.date = date;
		this.adress = adress;
		this.association = association;
	}


	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date eventDate) {
		this.date = eventDate;
	}
	
	public String getAdress() {
		return adress;
	}
	
	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Association getAssociation() {
		return association;
	}

	public void setAssociation(Association association) {
		this.association = association;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}
	
	


}
