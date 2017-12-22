package ops.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ops.inventory.dao.DiskSpaceRepository;
import ops.inventory.dao.model.DiskSpace;

@Service
public class DiskSpaceService {

	@Autowired DiskSpaceRepository diskSpaceRepository;

	@Transactional(readOnly = false)
	public DiskSpace  saveApplication(DiskSpace diskSpace) {
		return diskSpaceRepository.save(diskSpace);
	}
	
	@Transactional(readOnly = true)
	public DiskSpace  findByName(String name) {
		return diskSpaceRepository.findByName(name);
	}
}
