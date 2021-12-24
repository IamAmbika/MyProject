package generic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel {

	public static String getRowData(String path, String sheet, int row, int cell) {
		Workbook wb;
		String value = "";
		try {
			wb = WorkbookFactory.create(new FileInputStream(path));
			value = wb.getSheet(sheet).getRow(row).getCell(cell).toString();
		} catch (EncryptedDocumentException e) {

			e.printStackTrace();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return value;
	}

	public static int getRowCount(String path, String sheet) {
		Workbook wb;
		int rowCount = 0;
		try {
			wb = WorkbookFactory.create(new FileInputStream(path));
			rowCount = wb.getSheet(sheet).getLastRowNum();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rowCount;
	}
}
