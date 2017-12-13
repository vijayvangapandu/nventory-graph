package ops.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ops.inventory.dao.ApplicationRepository;
import ops.inventory.dao.model.Application;

@Service
public class ApplicationService {

	@Autowired ApplicationRepository appRepository;

	@Transactional(readOnly = false)
	public Application  saveApplication(Application application) {
		System.out.println("Saving application with name :" + application.getName());
		return appRepository.save(application);
	}
	
	@Transactional(readOnly = true)
	public Application  findByName(String name) {
		System.out.println("finding application with name :" + name);
		return appRepository.findByName(name);
	}
}
