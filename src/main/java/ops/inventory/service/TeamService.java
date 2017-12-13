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
		System.out.println("Saving team with name :" + team.getName());
		return teamRepository.save(team);
	}
	
	@Transactional(readOnly = true)
	public Team  findByName(String name) {
		System.out.println("finding Team with name :" + name);
		return teamRepository.findByName(name);
	}
}
