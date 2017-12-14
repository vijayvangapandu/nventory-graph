package ops.inventory.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ops.inventory.dao.model.Model;


@Repository
public interface ModelRepository extends PagingAndSortingRepository<Model, Long> {
	Model findByName(@Param("name") String name);
}
