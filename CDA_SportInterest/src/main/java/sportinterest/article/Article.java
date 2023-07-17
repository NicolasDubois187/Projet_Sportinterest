package sportinterest.article;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sportinterest.association.Association;
import sportinterest.user.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(length = 5000)
	private String description;
	@ManyToOne
	private Association association;
	@ManyToOne
	private User author;
	private Timestamp creationDate;
}
