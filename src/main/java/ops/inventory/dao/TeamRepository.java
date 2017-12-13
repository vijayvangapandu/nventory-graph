package ops.inventory.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ops.inventory.dao.model.Team;


@Repository
public interface TeamRepository extends PagingAndSortingRepository<Team, Long> {
	Team findByName(@Param("name") String name);
}
