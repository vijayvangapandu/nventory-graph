package ops.inventory.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ops.inventory.dao.model.Server;


@Repository
public interface ServerRepository extends PagingAndSortingRepository<Server, Long> {

	Server findByName(@Param("name") String name);

	Collection<Server> findByNameLike(@Param("name") String title);

	@Query("MATCH (m:Server)<-[r:NODE_OF]-(a:Application) RETURN m,r,a LIMIT {limit}")
	Collection<Server> graph(@Param("limit") int limit);
	
	@Query("MATCH (a:Application)-[r:NODE_OF]-(s:Server)-[ah]-(h) WHERE a.name = {0} RETURN a,r,s,ah,h")
	List<Server> getServersForApplication(String name);
	
}

