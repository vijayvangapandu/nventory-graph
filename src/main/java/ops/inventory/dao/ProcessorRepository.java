package ops.inventory.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ops.inventory.dao.model.Processor;


@Repository
public interface ProcessorRepository extends PagingAndSortingRepository<Processor, Long> {
	Processor findByName(@Param("name") String name);
}
