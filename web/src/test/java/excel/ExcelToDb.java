package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @author huqinsong
 * @date 2019/3/13
 */
public class ExcelToDb {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\user003\\Desktop\\新建 XLSX 工作表.xlsx");
        JDBC jdbc = new JDBC();
        jdbc.initDB();
        try {
            InputStream inputStream = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(inputStream);
            inputStream.close();
            Sheet sheet = workbook.getSheetAt(7);
            int rowLength = sheet.getLastRowNum()+1;
            for (int i = 0; i < rowLength; i++) {
                Row row = sheet.getRow(i);
                int colLength = row.getLastCellNum();
                String[] arr = new String[4];
                for (int j = 0; j < colLength; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null){
                        cell.setCellType(CellType.STRING);
                    }
                    arr[j] = cell.getStringCellValue();
                }
                jdbc.insert(arr[0], arr[1], arr[2], arr[3]);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbc.destroyDB();
        }
    }
}
