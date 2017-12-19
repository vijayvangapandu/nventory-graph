package ops.inventory.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ops.inventory.rest.ServerSaveRequest;

@Service
public class InventoryRecordsFileReader {

	private static final Logger logger = LoggerFactory.getLogger(InventoryRecordsFileReader.class);

	private static final String FILE_BASE_PATH = "/data/svc/";
	public static final String PROD_ENV = "PROD";
	public static final String NONPROD_ENV = "NONPROD";

	public static final String DATACENTER_1 = "DC1";
	public static final String DATACENTER_2 = "DC2";

	public List<ServerSaveRequest> loadDataFromFile(String fileName) throws Exception {
		File myFile = new File(FILE_BASE_PATH + fileName);
		FileInputStream fis = new FileInputStream(myFile);
		// Finds the workbook instance for XLSX file
		XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
		// Return first sheet from the XLSX workbook
		XSSFSheet mySheet = myWorkBook.getSheetAt(0);
		// Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator = mySheet.iterator();
		// Traversing over each row of XLSX file
		// Skip first row
		rowIterator.next();
		List<ServerSaveRequest> serverRequestsList = new ArrayList<>();

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			logger.info("Mapping row number: {} ", row.getRowNum());
			ServerSaveRequest request = new ServerSaveRequest();
			// For each row, iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			boolean badRow = false;
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				int columnIndex = cell.getColumnIndex();
				String value = readStringValue(cell);
				if (value == null) {
					badRow = true;
				}
				switch (columnIndex) {
				case 0:
					request.setServerName(value);
					break;
				case 1:
					request.setApplicationName(value);
					break;
				case 2:
					request.setTeamName(value);
					break;
				case 3:
					request.setModel(value);
					break;
				case 4:
					request.setSerialNumber(value);
					break;
				case 5:
					request.setOperatingSystem(value);
					break;
				case 6:
					float memoryInBytes = (StringUtils.isNotBlank(value)) ? Float.valueOf(value) : 0;
					request.setMemoryInGB((memoryInBytes / (1024 * 1024)));
					break;
				case 7:
					request.setCpuCores((StringUtils.isNotBlank(value)) ? Float.valueOf(value) : 0);
					break;
				case 8:
					request.setGateway(value);
					break;
				case 9:
					request.setIpAddress(value);
					break;
				case 10:
					request.setKernalRelease(value);
					break;
				case 11:
					request.setDiskSpaceInGB((StringUtils.isNotBlank(value)) ? Float.valueOf(value) : 0);
					break;
				case 13:
					request.setCost((StringUtils.isNotBlank(value)) ? Float.valueOf(value) : 0);
					break;
				}
			}
			if (!badRow) {
				request.setDataCenter(getDataCenter(request));
				request.setEnvironment(getEnvironment(request));
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
		default:
			logger.info("UNKNOWN type : [}", cell.getCellType());
			return null;
		}
	}

	private String getEnvironment(ServerSaveRequest request) {
		String serverName = request.getServerName();
		if (serverName.toLowerCase().contains(".prod.") || serverName.toLowerCase().contains(".prd.") ||serverName.toLowerCase().contains(".prd1.")
				|| serverName.toLowerCase().contains(".prd2.")
				|| serverName.toLowerCase().contains(".prod1.")
				|| serverName.toLowerCase().contains(".prod2.")) {
			return PROD_ENV;
		}

		if (serverName.toLowerCase().contains(".np.") || serverName.toLowerCase().contains(".qa.")
				|| serverName.toLowerCase().contains(".dev.")) {
			return NONPROD_ENV;
		}

		if (serverName.toLowerCase().contains(".r1.") || serverName.toLowerCase().contains(".r2.")
				|| serverName.toLowerCase().contains(".r3.") || serverName.toLowerCase().contains(".r4.")
				|| serverName.toLowerCase().contains(".r5.") || serverName.toLowerCase().contains(".r6.")
				|| serverName.toLowerCase().contains(".r7.") || serverName.toLowerCase().contains(".r8.")
				|| serverName.toLowerCase().contains(".r9.")) {

			return NONPROD_ENV;
		}

		if (serverName.toLowerCase().contains(".qa1.") || serverName.toLowerCase().contains(".qa2.")
				|| serverName.toLowerCase().contains(".qa3.") || serverName.toLowerCase().contains(".qa4.")
				|| serverName.toLowerCase().contains(".qa5.") || serverName.toLowerCase().contains(".qa6.")
				|| serverName.toLowerCase().contains(".qa7.") || serverName.toLowerCase().contains(".qa8.")
				|| serverName.toLowerCase().contains(".qa9.")) {

			return NONPROD_ENV;
		}

		logger.info("Unable to derive environment, considering as Prod for server {}", serverName);

		return PROD_ENV;
	}

	private String getDataCenter(ServerSaveRequest request) {
		String serverName = request.getServerName();
		if (serverName.toLowerCase().contains(".dc2.")) {
			return DATACENTER_2;
		}
		return DATACENTER_1;
	}

}
