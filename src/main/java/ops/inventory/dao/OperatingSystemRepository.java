package ops.inventory.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ops.inventory.dao.model.OperatingSystem;


@Repository
public interface OperatingSystemRepository extends PagingAndSortingRepository<OperatingSystem, Long> {
	OperatingSystem findByName(@Param("name") String name);
}
