package ops.inventory.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import ops.inventory.rest.ServerSaveRequest;

@Service
public class InventoryRecordsFileReader {

	/*
	 0 : ServerName	                          1 : ApplicationName	2 : Team	    3 : Model	                4 : SerialNumber	5 : OS	        6 : RAM (KB)	7 : vCPU	8 : Gateway	     9 : IPAddress	        10 : KernelRelease	11 : TotalDiskSpace (GB)	12 : HardDisks	
     0 : accountmanager.np.dc1.eharmony.com	  1 : AccountManager	2 : Operations	3 : VMware Virtual Platform	4 : VMware	        5 : CentOS5.5	6 : 1026880.0	7 : 1.0	    8 : 10.9.80.1	 9 : 10.9.81.49/23   	10 : 2.6.18-194.el5	11 : 21.4	                12 : hda 21.4 GB	
     0 : accountmanager.prod.dc1.eharmony.com 1 : AccountManager	2 : Operations	3 : VMware Virtual Platform	4 : VMware	        5 : CentOS5.5	6 : 2059580.0	7 : 1.0	    8 : 172.18.128.1 9 : 172.18.128.176/21 	10 : 2.6.18-194.el5	11 : 21.4	                12 : hda 21.4 GB	
    */
	
	

	public List<ServerSaveRequest> loadDataFromFile(String fileName) throws Exception {
		File myFile = new File(fileName); 
		FileInputStream fis = new FileInputStream(myFile); 
		// Finds the workbook instance for XLSX file 
		XSSFWorkbook myWorkBook = new XSSFWorkbook (fis); 
		// Return first sheet from the XLSX workbook 
		XSSFSheet mySheet = myWorkBook.getSheetAt(0); 
		// Get iterator to all the rows in current sheet 
		Iterator<Row> rowIterator = mySheet.iterator(); 
		// Traversing over each row of XLSX file 
		//Skip first row
		rowIterator.next(); 
		List<ServerSaveRequest> serverRequestsList = new ArrayList<>();
		
		while (rowIterator.hasNext()) { 
			Row row = rowIterator.next(); 
			System.out.println("Mapping row number: "+ row.getRowNum()); 
			ServerSaveRequest request = new ServerSaveRequest();
			// For each row, iterate through each columns 
			Iterator<Cell> cellIterator = row.cellIterator(); 
			boolean badRow = false;
			while (cellIterator.hasNext()) { 
				Cell cell = cellIterator.next(); 
				int columnIndex = cell.getColumnIndex();
				String value = readStringValue(cell);
				if(value == null) {
					badRow = true;
				}
				switch(columnIndex) {
					case 0: request.setServerName(value);break;
					case 1: request.setApplicationName(value);break;
					case 2: request.setTeamName(value);break;
					case 3: request.setModel(value);break;
					case 4: request.setSerialNumber(value);break;
					case 5: request.setOperatingSystem(value);break;
					case 6: request.setMemoryInGB(value);break;
					case 7: request.setCpuCores(value);break;
					case 8: request.setGateway(value);break;
					case 9: request.setIpAddress(value);break;
					case 10: request.setKernalRelease(value);break;
					case 11: request.setDiskSpaceInGB(value);break;
				}
			}
			if(!badRow) {
				serverRequestsList.add(request);
			}
			
		}
		
		return serverRequestsList;

	}
	
	private String readStringValue(Cell cell) {
		
		switch (cell.getCellType()) { 
		case Cell.CELL_TYPE_STRING: 
			return cell.getStringCellValue();
	    case Cell.CELL_TYPE_NUMERIC: 
		   return String.valueOf(cell.getNumericCellValue());
		case Cell.CELL_TYPE_BOOLEAN: 
			return String.valueOf(cell.getBooleanCellValue());
	   default : System.out.println("UNKNOWN type :"+ cell.getCellType()); return null;
	}
	}
	
}
