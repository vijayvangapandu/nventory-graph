package ops.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ops.inventory.dao.ProcessorRepository;
import ops.inventory.dao.model.Processor;

@Service
public class ProcessorService {

	@Autowired ProcessorRepository processorRepository;

	@Transactional(readOnly = false)
	public Processor  saveApplication(Processor processor) {
		System.out.println("Saving processor with name :" + processor.getName());
		return processorRepository.save(processor);
	}
	
	@Transactional(readOnly = true)
	public Processor  findByName(String name) {
		System.out.println("finding processor with name :" + name);
		return processorRepository.findByName(name);
	}
}
