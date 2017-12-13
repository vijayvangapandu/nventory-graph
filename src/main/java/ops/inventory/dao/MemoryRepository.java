package ops.inventory.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ops.inventory.dao.model.Memory;


@Repository
public interface MemoryRepository extends PagingAndSortingRepository<Memory, Long> {
	Memory findByName(@Param("name") String name);
}
