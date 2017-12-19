package ops.inventory.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ops.inventory.dao.model.AllocatedCpu;
import ops.inventory.dao.model.AllocatedDiskSpace;
import ops.inventory.dao.model.AllocatedMemory;
import ops.inventory.dao.model.Server;


@Repository
public interface ServerRepository extends PagingAndSortingRepository<Server, Long> {

	Server findByName(@Param("name") String name);

	Collection<Server> findByNameLike(@Param("name") String title);

	@Query("MATCH (m:Server)<-[r:NODE_OF]-(a:Application) RETURN m,r,a LIMIT {limit}")
	Collection<Server> graph(@Param("limit") int limit);
	
	@Query("MATCH (a:Application)-[r:NODE_OF]-(s:Server)-[ah]-(h) WHERE a.name = {0} RETURN a,r,s,ah,h")
	List<Server> getServersForApplication(String name);
	
	@Query("MATCH (a:Application)-[r:NODE_OF]-(s:Server)-[m:ALLOCATED_MEMORY]-() WHERE a.name = {0} RETURN m ")
	List<AllocatedMemory> getServersMemoryByApplication( String name);
	
	@Query("MATCH (a:Application{id: {0}})-[r:NODE_OF]-(s:Server)-[c:ALLOCATED_CPU]-() RETURN c ")
	//List<AllocatedCpu> getServersCPUByApplication(@Param(value="name") String name);
	List<AllocatedCpu> getServersCPUByApplication(long id);
	
	@Query("MATCH (a:Application)-[r:NODE_OF]-(s:Server)-[d: ALLOCATED_DISK]-() WHERE a.name = {0} RETURN d ")
	List<AllocatedDiskSpace> getServersDiskSpaceByApplication(String name);
	
}

