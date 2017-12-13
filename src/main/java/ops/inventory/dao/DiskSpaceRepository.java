package ops.inventory.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ops.inventory.dao.model.DiskSpace;


@Repository
public interface DiskSpaceRepository extends PagingAndSortingRepository<DiskSpace, Long> {
	DiskSpace findByName(@Param("name") String name);
}
