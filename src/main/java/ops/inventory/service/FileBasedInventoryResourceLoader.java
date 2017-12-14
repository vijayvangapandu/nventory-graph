package ops.inventory.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ops.inventory.dao.model.Server;
import ops.inventory.rest.ServerSaveRequest;

@Service
public class FileBasedInventoryResourceLoader {

	@Autowired InventoryService inventoryService;

	private ServerSaveRequest buildServerSaveRequest(String serverName, String applicationName) {
		ServerSaveRequest request = new ServerSaveRequest();
		
		request.setApplicationName(applicationName);
		request.setServerName(serverName);
		request.setCpuCores("2");
		request.setDiskSpaceInGB("32");
		request.setMemoryInGB("8");
		request.setTeamName("Singles");
		request.setEnvironment(getEnvironment(serverName));
		request.setDataCenter(getDataCenter(serverName));
		request.setOperatingSystem("CentOS5.5");
		request.setModel("VMware Virtual Platform");
		
		return request;
	}
	
	private String getEnvironment(String serverName) {
		if(serverName.contains(".prod.")) {
			return "PROD";
		}
		
		if(serverName.contains(".prod1.")) {
			return "PROD";
		}
		
		if(serverName.contains(".prod2.")) {
			return "PROD";
		}
		
		if(serverName.contains(".np.")) {
			return "NONPROD";
		}
		
		if(serverName.contains(".qa.")) {
			return "NONPROD";
		}
		
		if(serverName.contains(".r3.")) {
			return "NONPROD";
		}
		
		if(serverName.contains(".r9.")) {
			return "NONPROD";
		}
		
		if(serverName.contains(".r6.")) {
			return "NONPROD";
		}
		
		return "PROD";
	}
	
	private String getDataCenter(String serverName) {
		if(serverName.contains(".dc2.")) {
			return "DC2";
		}
		return "DC1";
	}
	
	public void loadInventoryData(String fileName) {
		String serverName = "papi1.prod.dc1.eharmony.com";
		String applicationName = "PAPI";
		
		ServerSaveRequest request = buildServerSaveRequest(serverName, applicationName);
		inventoryService.saveServer(request);
		
	}
	
	public Server saveServerWithName(String serverName, String applicationName) {
		
		ServerSaveRequest request = buildServerSaveRequest(serverName, applicationName);
		return inventoryService.saveServer(request);
		
	}
	
	public void loadDataFromFile(String fileName) throws Exception {
		File myFile = new File("/Users/vvangapandu/Desktop/inventory-load-2017-Part1.xlsx"); 
		FileInputStream fis = new FileInputStream(myFile); 
		// Finds the workbook instance for XLSX file 
		XSSFWorkbook myWorkBook = new XSSFWorkbook (fis); 
		// Return first sheet from the XLSX workbook 
		XSSFSheet mySheet = myWorkBook.getSheetAt(0); 
		// Get iterator to all the rows in current sheet 
		Iterator<Row> rowIterator = mySheet.iterator(); 
		// Traversing over each row of XLSX file 
		while (rowIterator.hasNext()) { 
			Row row = rowIterator.next(); 
			// For each row, iterate through each columns 
			Iterator<Cell> cellIterator = row.cellIterator(); 
			while (cellIterator.hasNext()) { 
				Cell cell = cellIterator.next(); 
				switch (cell.getCellType()) { 
					case Cell.CELL_TYPE_STRING: 
						System.out.print(cell.getStringCellValue() + "\t"); 
						break; 
				    case Cell.CELL_TYPE_NUMERIC: 
					   System.out.print(cell.getNumericCellValue() + "\t"); 
					   break; 
					case Cell.CELL_TYPE_BOOLEAN: 
						System.out.print(cell.getBooleanCellValue() + "\t"); 
						break; 
				   default : 
				}
			}
			System.out.println(""); 
		}

		//Read more: http://www.java67.com/2014/09/how-to-read-write-xlsx-file-in-java-apache-poi-example.html#ixzz51CSowL60
	}
	
}
