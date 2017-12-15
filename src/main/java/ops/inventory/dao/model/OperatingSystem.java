package ops.inventory.dao.model;


import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity(label="OperatingSystem")
public class OperatingSystem {

	@GraphId
	private Long id;
	private String name;
	
	@Relationship(type = "INSTALLED_OS", direction = Relationship.INCOMING)
	private Set<ServerOSLink> installedOS = new HashSet<>();
	
	public OperatingSystem() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    public void addInstalledOS(ServerOSLink soLink) {
		
    	installedOS.add(soLink);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ServerOSLink> getInstalledOS() {
		return installedOS;
	}

	public void setInstalledOS(Set<ServerOSLink> installedOS) {
		this.installedOS = installedOS;
	}

}
