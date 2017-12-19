package ops.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ops.inventory.dao.MemoryRepository;
import ops.inventory.dao.model.Memory;

@Service
public class MemoryService {

	@Autowired MemoryRepository memoryRepository;

	@Transactional(readOnly = false)
	public Memory  saveApplication(Memory memory) {
		return memoryRepository.save(memory);
	}
	
	@Transactional(readOnly = true)
	public Memory  findByName(String name) {
		return memoryRepository.findByName(name);
	}
}
