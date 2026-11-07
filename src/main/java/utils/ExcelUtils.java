package utils;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {
    public static Workbook workbook;
    public static Sheet sheet;

    public static void loadExcel(String filepath,String sheetname) throws IOException {
        FileInputStream file = new FileInputStream(filepath);
        workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheet(sheetname);
    }
    public static String getCellData(int row,int col){
        Cell cell = sheet.getRow(row).getCell(col);
        if(cell.getCellType() == CellType.STRING){
            return cell.getStringCellValue();
        }
        else if(cell.getCellType() == CellType.NUMERIC){
            String.valueOf((int) cell.getNumericCellValue());
        }
        return "";
    }

    public static int getRowCount(){
        return sheet.getPhysicalNumberOfRows();
    }
    public static void closeExcel() throws IOException {
        workbook.close();
    }
}
