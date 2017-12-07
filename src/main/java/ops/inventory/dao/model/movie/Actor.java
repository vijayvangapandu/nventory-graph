package ops.inventory.dao.model.movie;


import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author Mark Angrish
 */
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity
public class Actor extends Person {

	@Relationship(type = "ACTED_IN", direction = Relationship.OUTGOING)
    Set<Role> roles = new HashSet<Role>();

    /*public Role playedIn(Movie movie, String roleName) {
        final Role role = new Role(this, movie, roleName);
        roles.add(role);
        movie.addRole(role);
        return role;
    }*/
}
