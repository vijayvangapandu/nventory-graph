package ops.inventory.dao;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ops.inventory.dao.model.Application;


@Repository
public interface ApplicationRepository extends PagingAndSortingRepository<Application, Long> {
	Application findByName(@Param("name") String name);
	
	@Query("MATCH (a:Application) RETURN a")
	List<Application> getAllApplications();
}
