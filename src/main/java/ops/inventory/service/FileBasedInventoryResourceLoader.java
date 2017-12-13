package ops.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ops.inventory.dao.ApplicationRepository;
import ops.inventory.dao.model.Application;

@Service
public class FileBasedInventoryResourceLoader {

	@Autowired ApplicationRepository appRepository;

	@Transactional(readOnly = false)
	public boolean  loadApplicationsInventory(final String resourcePath) {
		System.out.println("loading applications configuration with path :" + resourcePath);
		
		
		return true;
		
	}
	
	@Transactional(readOnly = true)
	public Application  findByName(String name) {
		System.out.println("finding application with name :" + name);
		return appRepository.findByName(name);
	}
	
}
