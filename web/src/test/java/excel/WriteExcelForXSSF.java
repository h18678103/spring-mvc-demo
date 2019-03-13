package excel;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * Created by M_WBCG on 2017/7/14.
 */
public class WriteExcelForXSSF {
    public void write() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("0");
        Row row = sheet.createRow(0);
        CellStyle cellStyle = workbook.createCellStyle();

        row.createCell(0).setCellStyle(cellStyle);
        row.createCell(0).setCellValue("姓名");

        row.createCell(1).setCellStyle(cellStyle);
        row.createCell(1).setCellValue("年龄");

        workbook.setSheetName(0, "信息");
        try {
            File file = new File("././POI/POI3.xlsx");
            FileOutputStream fileoutputStream = new FileOutputStream(file);
            workbook.write(fileoutputStream);
            fileoutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new WriteExcelForXSSF().write();
    }
}

