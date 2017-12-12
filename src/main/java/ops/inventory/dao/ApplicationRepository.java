package ops.inventory.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ops.inventory.dao.model.Application;


@Repository
public interface ApplicationRepository extends PagingAndSortingRepository<Application, Long> {
	Application findByName(@Param("name") String name);
}
