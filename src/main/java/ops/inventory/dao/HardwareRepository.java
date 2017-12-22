package ops.inventory.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ops.inventory.dao.model.Hardware;


@Repository
public interface HardwareRepository extends PagingAndSortingRepository<Hardware, Long> {
	Hardware findByName(@Param("name") String name);
}
