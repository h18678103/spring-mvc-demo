package excel;

import org.apache.poi.ss.usermodel.*;

import java.io.*;

/**
 * Created by M_WBCG on 2017/7/14.
 */
public class ReadExcelForXSSF {
    public void read() {
        File file = new File("C:\\Users\\user003\\Desktop\\新建 XLSX 工作表.xlsx");
        try {
            InputStream inputStream = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(inputStream);
            inputStream.close();
            Sheet sheet = workbook.getSheetAt(6);
            int rowLength = sheet.getLastRowNum()+1;
            for (int i = 0; i < rowLength; i++) {
                Row row = sheet.getRow(i);
                int colLength = row.getLastCellNum();
                for (int j = 0; j < colLength; j++) {
                    Cell cell = row.getCell(j);
                    //Excel数据Cell有不同的类型，当我们试图从一个数字类型的Cell读取出一个字符串时就有可能报异常：
                    //Cannot get a STRING value from a NUMERIC cell
                    //将所有的需要读的Cell表格设置为String格式
                    if (cell != null){
                        cell.setCellType(CellType.STRING);
                    }
                    System.out.print(cell.getStringCellValue() + "\t");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ReadExcelForXSSF().read();
    }
}
