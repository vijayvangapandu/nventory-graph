package ops.inventory.dao;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import ops.inventory.dao.model.movie.Role;

@Repository
public interface PersonActedOnMovieRepository extends GraphRepository<Role> {
	
}
