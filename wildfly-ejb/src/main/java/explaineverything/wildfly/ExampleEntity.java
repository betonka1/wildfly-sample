package explaineverything.wildfly;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

@DynamicUpdate
@Entity
public class ExampleEntity {
	
	private int id;
	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(nullable = false, length = 128)
	@Length(min = 1, max = 128)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
