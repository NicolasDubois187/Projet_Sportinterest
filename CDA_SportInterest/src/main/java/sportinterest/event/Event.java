package sportinterest.event;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sportinterest.association.Association;
import sportinterest.report.Report;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(length = 1000)
	private String description;
	private Timestamp date;
	private String address;
	@Column(name = "restricted", columnDefinition = "BOOLEAN")
	private Boolean restricted;
	@ManyToOne
	private Association association;
    @OneToOne
	private Report report;
	private Timestamp creationDate;

}
