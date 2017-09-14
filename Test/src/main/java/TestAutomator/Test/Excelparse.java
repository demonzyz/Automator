package TestAutomator.Test;


import java.io.FileInputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class Excelparse {
	
	private  String filePath;
	private FileInputStream fin;
	private Object object[][];
//	private Map<String, String> map;
	
	public Excelparse(String filePath) {
		this.filePath = filePath;
	}
	
	@SuppressWarnings("resource")
	public String getCell(String Attributes, int row, int Column) {
		
		try {
			fin = new FileInputStream(filePath);
			POIFSFileSystem poifs = new POIFSFileSystem(fin);	
			HSSFWorkbook wb = new HSSFWorkbook(poifs);
			HSSFSheet sheet = wb.getSheet(Attributes);
			HSSFRow sheetRow = sheet.getRow(row);
			HSSFCell sheetCell = sheetRow.getCell(Column);
			sheetCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			String cell = sheetCell.getStringCellValue();
			wb.close();
			return cell;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("未读取到第" + row + "行，第" + Column + "列的单元格内信息");
		}
		
		return null;
	}
	
	public Object[][] getAllCell(String Attributes) {
		try {
			fin = new FileInputStream(filePath);
			POIFSFileSystem poifs = new POIFSFileSystem(fin);	
			HSSFWorkbook wb = new HSSFWorkbook(poifs);
			HSSFSheet sheet = wb.getSheet(Attributes);
			int rowNumber = sheet.getLastRowNum();
			
			object = new Object[rowNumber][];
			for (int i = 1; i < rowNumber+1; i++) {
				HSSFRow sheetRow = sheet.getRow(i);
				int Column = sheetRow.getLastCellNum();
				Map<String, String> map = new LinkedHashMap<>();
				for (int k = 0; k < Column; k++) {
					HSSFCell sheetCell = sheetRow.getCell(k);
					sheetCell.setCellType(HSSFCell.CELL_TYPE_STRING);
					String cell = sheetCell.getStringCellValue();
					String key = sheet.getRow(0).getCell(k).getStringCellValue();
					
					//不能在数组定义的时候，给map添加值。不能连着写 要不map的put不生效
					map.put(key, cell);
					Object[] temp = new Object[]{map};
					
					//你前面想去除去第一行 所以i从1开始循环  但是object的下标不能从1开始赋值
					object[i-1] = temp;
				}	
			}
			wb.close();
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("未读取到" + filePath + "的文件信息");
		}
		return null;
	}
	
	public static void main(String[] args) {
		Excelparse excelparse = new Excelparse("/Users/zhi/Downloads/shishi.xls");
		String value = excelparse.getCell("工作表1", 1, 1);
		System.out.println(value);
		
		Object[][] bObjects = excelparse.getAllCell("工作表1");
		for (int i = 0; i < bObjects.length; i++) {
			for (int j = 0; j < bObjects[i].length; j++) {
				System.out.println(bObjects[i][j]);
			}
//			Map<String, String> bMap = new LinkedHashMap<>();
//			Object btemp = bObjects[i][bMap];
		}

		
	}

}
