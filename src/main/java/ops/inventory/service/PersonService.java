package ops.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ops.inventory.dao.PersonActedOnMovieRepository;
import ops.inventory.dao.PersonRepository;
import ops.inventory.dao.model.movie.Person;
import ops.inventory.dao.model.movie.Role;

@Service
public class PersonService {

	@Autowired PersonRepository personRepository;
	@Autowired PersonActedOnMovieRepository roleRepository;

	@Transactional(readOnly = false)
	public Person  savePerson(Person p) {
		
		return personRepository.save(p);
	}
	
	@Transactional(readOnly = false)
	public Person  findByName(String name) {
		
		return personRepository.findByName(name);
	}
	@Transactional(readOnly = true)
	public List<Person> findAllPersons() {
		List<Person> persons = new ArrayList();
		Iterable<Person> personsIte = personRepository.findAll();
		if(personsIte != null) {
			personsIte.iterator().forEachRemaining(a -> {
				persons.add(a);
			});
		}
		return persons;
	}
	
	@Transactional(readOnly = false)
	public Role  saveRole(Role r) {
		return roleRepository.save(r);
	}
}
