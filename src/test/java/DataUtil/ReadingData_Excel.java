package DataUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ReadingData_Excel{
	
	private static final String TEST_DATA_SHEET_PATH = "/Users/pavanivemula/Documents/Workspace/OpenCart/src/test/java/DataUtil/ReadData.xlsx";
	private static Workbook book;
	private static Sheet sheet;

	public static Object[][] getTestData(String sheetName) {
	 Object data[][] = null;
     try {
	  FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
	  book = WorkbookFactory.create(ip);
	  sheet = book.getSheet(sheetName);
	  
	  DataFormatter dataFormatter= new DataFormatter();
	  int rowCount = sheet.getLastRowNum();
      int coloumnCount = sheet.getRow(0).getPhysicalNumberOfCells();
      
      data = new Object[rowCount][coloumnCount];
      for (int i=0; i<rowCount;i++) {
    	  Row row = sheet.getRow(i + 1);
		  for (int j=0; j<coloumnCount;j++) {
			Cell cell = row.getCell(j);  
			  data[i][j] = dataFormatter.formatCellValue(cell);
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return data;
    }
}


 
	
