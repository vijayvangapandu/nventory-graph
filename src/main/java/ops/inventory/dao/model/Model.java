package ops.inventory.dao.model;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@NodeEntity(label="Model")
public class Model {

	@GraphId
	private Long id;
	private String name;

	@Relationship(type = "MODEL_OF", direction = Relationship.INCOMING)
	private Set<ServerModelLink> models = new HashSet<>();

	public Model() {

	}

	public Model(String name, String model, String sn) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void addServerModelLink(ServerModelLink smLink) {

		models.add(smLink);
	}

	public Set<ServerModelLink> getModels() {
		return models;
	}

	public void setModels(Set<ServerModelLink> models) {
		this.models = models;
	}

	public void setName(String name) {
		this.name = name;
	}

}
