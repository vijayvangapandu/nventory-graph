package ops.inventory.dao.model.movie;


import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity
public class Person {

	@GraphId
	private Long id;

	private String name;

	private int born;

	/*@Relationship(type = "ACTED_IN",direction = Relationship.OUTGOING)
	private List<Movie> movies = new ArrayList<>();*/
	
	@Relationship(type = "ACTED_IN", direction = Relationship.OUTGOING)
    Set<Role> roles = new HashSet<Role>();

    public Role playedIn(Movie movie, String roleName) {
        final Role role = new Role(movie, this, roleName);
        roles.add(role);
        movie.addRole(role);
        return role;
    }

	public Person() {
	}
	
	public void addRole(Role role) {
		roles.add(role);
	}

	public Person(String name) {

		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getBorn() {
		return born;
	}

	public Set<Role> getRoles() {
		return roles;
	}
	
	public void setId(long id) {
		this.id = id;
	}
}
