package ops.inventory.dao.model;


import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity
public class Server {

	@GraphId
	private Long id;
	private String name;
	private String environment;
	private String ram;
	private String disk;
	private String cpu;
	private String os;

	@Relationship(type = "NODE_OF", direction = Relationship.INCOMING)
	private List<ApplicationServerLink> appServerLinks = new ArrayList<>();
	

	public Server() {
	}
	
	public Server(String name) {

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
	
	public void addApplicationServerLink(ApplicationServerLink link) {
		appServerLinks.add(link);
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getDisk() {
		return disk;
	}

	public void setDisk(String disk) {
		this.disk = disk;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public List<ApplicationServerLink> getAppServerLinks() {
		return appServerLinks;
	}

	public void setAppServerLinks(List<ApplicationServerLink> appServerLinks) {
		this.appServerLinks = appServerLinks;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
