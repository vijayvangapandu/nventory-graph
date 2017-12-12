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
import ops.inventory.dao.model.ApplicationServerLink;
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
			int target = i;
			i++;
			for (ApplicationServerLink role : server.getAppServerLinks()) {
				Map<String, Object> actor = map("name", role.getApplication().getName(), "label", "application");
				int source = nodes.indexOf(actor);
				if (source == -1) {
					nodes.add(actor);
					source = i++;
				}
				rels.add(map("source", source, "target", target));
			}
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
		System.out.println("Result resulting map.." + result != null ? result.size():-2);
		Map<String, Object> map =  toD3Format(result);
		System.out.println("Returning resulting map.." + map != null ? map.size():-1);
		return map;
	}
	
	@Transactional(readOnly = false)
	public Server  saveServer(Server server) {
		System.out.println("Saving server with name :" + server.getName());
		return serverRepository.save(server);
	}
}
