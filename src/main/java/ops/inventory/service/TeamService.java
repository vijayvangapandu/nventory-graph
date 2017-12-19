package ops.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ops.inventory.dao.TeamRepository;
import ops.inventory.dao.model.Team;

@Service
public class TeamService {

	@Autowired TeamRepository teamRepository;

	@Transactional(readOnly = false)
	public Team  saveApplication(Team team) {
		return teamRepository.save(team);
	}
	
	@Transactional(readOnly = true)
	public Team  findByName(String name) {
		return teamRepository.findByName(name);
	}
}
