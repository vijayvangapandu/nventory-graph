package ops.inventory.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ops.inventory.dao.ServerRepository;
import ops.inventory.dao.model.Server;

@Service
public class ServerService {

	@Autowired ServerRepository serverRepository;

	private Map<String, Object> toD3Format(Collection<Server> movies) {
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();
		int i = 0;
		Iterator<Server> result = movies.iterator();
		while (result.hasNext()) {
			Server server = result.next();
			nodes.add(map("name", server.getName(), "label", "server"));
			i++;
		}
		return map("nodes", nodes, "links", rels);
	}

	private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put(key1, value1);
		result.put(key2, value2);
		return result;
	}

	@Transactional(readOnly = true)
	public Map<String, Object>  graph(int limit) {
		Collection<Server> result = serverRepository.graph(limit);
		Map<String, Object> map =  toD3Format(result);
		return map;
	}
	
	@Transactional(readOnly = false)
	public Server  findByName(String name) {
		return serverRepository.findByName(name);
	}
	
	@Transactional(readOnly = false)
	public Server  saveServer(Server server) {
		return serverRepository.save(server);
	}
	
	@Transactional(readOnly = false)
	public void  deleteServer(long id) {
		serverRepository.delete(id);
	}
}
