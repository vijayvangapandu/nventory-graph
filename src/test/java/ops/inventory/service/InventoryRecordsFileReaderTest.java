package ops.inventory.service;

import java.util.List;

import org.junit.Assert;

import ops.inventory.rest.ServerSaveRequest;

public class InventoryRecordsFileReaderTest {

	private InventoryRecordsFileReader testClass = new InventoryRecordsFileReader();
	
	final String fileName = "/Users/vvangapandu/Desktop/inventory-load-2017-Part1-Copy.xlsx";
	//@Test
	public void testFileRead() throws Exception {
		List<ServerSaveRequest> list = testClass.loadDataFromFile(fileName);
		Assert.assertNotNull(list);
		for(ServerSaveRequest request: list) {
			System.out.println(request + " : " + request.getServerName());
		}
	}
	
	
}
