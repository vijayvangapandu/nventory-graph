package ops.inventory.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ops.inventory.dao.model.Department;


@Repository
public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long> {
	Department findByName(@Param("name") String name);
}
