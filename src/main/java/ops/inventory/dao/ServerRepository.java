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


//@RepositoryRestResource(collectionResourceRel = "servers", path = "servers")
@Repository
public interface ServerRepository extends PagingAndSortingRepository<Server, Long> {

	Server findByName(@Param("name") String name);

	Collection<Server> findByNameLike(@Param("name") String title);

	@Query("MATCH (m:Server)<-[r:NODE_OF]-(a:Application) RETURN m,r,a LIMIT {limit}")
	Collection<Server> graph(@Param("limit") int limit);
	
	@Query("MATCH (a:Application)-[r:NODE_OF]-(s:Server) WHERE a.name = {name} RETURN s ")
	//@Query("start user=node({userId}) match user-[u:DT_USER_CREATES_EVENT]->(eventnode) WHERE eventnode.status = 1 AND eventnode.updatedDate >= {startDate} return eventnode;")
	List<Server> getServersForApplication(@Param(value="name") String name);
	
	@Query("MATCH (a:Application)-[r:NODE_OF]-(s:Server)-[m:ALLOCATED_MEMORY]-() WHERE a.name = '{name}' RETURN m ")
	List<AllocatedMemory> getServersMemoryByApplication(@Param(value="name") String name);
	
	@Query("MATCH (a:Application{id: {param}.id})-[r:NODE_OF]-(s:Server)-[c:ALLOCATED_CPU]-() RETURN c ")
	//List<AllocatedCpu> getServersCPUByApplication(@Param(value="name") String name);
	List<AllocatedCpu> getServersCPUByApplication(@Param(value="id") long id);
	
	@Query("MATCH (a:Application)-[r:NODE_OF]-(s:Server)-[d: ALLOCATED_DISK]-() WHERE a.name = '{name}' RETURN d ")
	List<AllocatedDiskSpace> getServersDiskSpaceByApplication(@Param(value="name") String name);
	
}

