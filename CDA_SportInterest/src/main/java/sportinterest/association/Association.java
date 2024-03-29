package sportinterest.association;

import jakarta.persistence.*;



@Entity
public class Association {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(length = 5000)
	private String description;
	private int presidentId;

	public Association() {
		
	}
	
	public Association(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	
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

	public int getPresidentId() {
		return presidentId;
	}

	public void setPresidentId(int presidentId) {
		this.presidentId = presidentId;
	}
}
