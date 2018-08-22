package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;

/**
 * @since 2013-04-22

 * @author Gerrard

 * 获取excel中 图片，并得到图片位置，支持03 07 多sheet

 */

class School{
    public String postion;
    public String chinese;
    public String englist;
    public String pic;
}

public class GetImgFromExcel {

    static String  pre = "http://file.mixpay.test/cpa/payee/";

    //中文=英文
    static List<School> schools = new LinkedList<>();

    static Map<String, String> contryId = new HashMap<>();
    static {
        contryId.put("澳大利亚", "18");
        contryId.put("德国", "50");
        contryId.put("俄罗斯", "55");
        contryId.put("俄罗斯", "55");
        contryId.put("法国", "58");
        contryId.put("美国", "132");
        contryId.put("英国", "227");
    }
    /**
     * @param args
     * @throws IOException
     * @throws InvalidFormatException
     */
    public static void main(String[] args) throws InvalidFormatException, IOException {
        // 创建文件
        File file = new File("D:\\CPA\\学校\\学校.xlsx");
        // 创建流
        InputStream input = new FileInputStream(file);
        // 获取文件后缀名
        String fileExt =  file.getName().substring(file.getName().lastIndexOf(".") + 1);
        // 创建Workbook
        Workbook wb = null;
        // 创建sheet
        Sheet sheet = null;
        //根据后缀判断excel 2003 or 2007+
        if (fileExt.equals("xls")) {
            wb = WorkbookFactory.create(input);
        } else {
            wb = new XSSFWorkbook(input);
        }
        //获取excel sheet总数
        int sheetNumbers = wb.getNumberOfSheets();
        // sheet list
        List<Map<String, PictureData>> sheetList = new ArrayList<>();
        // 循环sheet
        for (int i = 0; i < sheetNumbers; i++) {
            sheet = wb.getSheetAt(i);
            // map等待存储excel图片
            Map<String, PictureData> sheetIndexPicMap;
            // 判断用07还是03的方法获取图片
            if (fileExt.equals("xls")) {
                sheetIndexPicMap = getSheetPictrues03(i, (HSSFSheet) sheet, (HSSFWorkbook) wb);
            } else {
                sheetIndexPicMap = getSheetPictrues07(i, (XSSFSheet) sheet, (XSSFWorkbook) wb);
                printSql();
            }
            // 将当前sheet图片map存入list
            sheetList.add(sheetIndexPicMap);
        }
        printImg(sheetList);
    }

    public static void printSql(){
        StringBuilder sql = new StringBuilder("INSERT INTO remit_payee_org (country_id, cn_name, en_name, logo_url) VALUES ");
        for (School school : schools){
            sql.append("('").append(getCountryId(school.chinese)).append("', '");
            sql.append(school.chinese).append("', '");
            sql.append(school.englist).append("', '");
            sql.append(school.pic).append("'),");
        }
        System.out.println(sql.toString());
    }

    private static String getCountryId(Object obj) {
        return contryId.get(getCountry(obj));
    }

    private static String getCountry(Object obj) {
        String school  = (String) obj;
        if (school==null){
            return "无";
        }
        System.out.println("school="+school);
        File d1 = new File("D:\\CPA\\学校\\学校收集");
        File[] d2 = d1.listFiles();
        for (File d20 : d2){
            String[] schoolNameDoc = d20.list();
            if (schoolNameDoc==null){
                continue;
            }
            for (String schoolName : schoolNameDoc){
                schoolName = schoolName.substring(0, schoolName.indexOf("."));
                if (school.equals(schoolName)){
                    return d20.getName();
                }
            }
        }
        return "无";
    }

    /**
     * 获取Excel2003图片
     * @param sheetNum 当前sheet编号
     * @param sheet 当前sheet对象
     * @param workbook 工作簿对象
     * @return Map key:图片单元格索引（0_1_1）String，value:图片流PictureData
     * @throws IOException
     */
    public static Map<String, PictureData> getSheetPictrues03(
            int sheetNum, HSSFSheet sheet, HSSFWorkbook workbook) {

        Map<String, PictureData> sheetIndexPicMap = new HashMap<>();
        List<HSSFPictureData> pictures = workbook.getAllPictures();
        if (pictures.size() != 0) {
            for (HSSFShape shape : sheet.getDrawingPatriarch().getChildren()) {
                HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();
                if (shape instanceof HSSFPicture) {
                    HSSFPicture pic = (HSSFPicture) shape;
                    int pictureIndex = pic.getPictureIndex() - 1;
                    HSSFPictureData picData = pictures.get(pictureIndex);
                    String picIndex = String.valueOf(sheetNum) + "_"
                            + String.valueOf(anchor.getRow1()) + "_"
                            + String.valueOf(anchor.getCol1());
                    sheetIndexPicMap.put(picIndex, picData);
                }
            }
            return sheetIndexPicMap;
        } else {
            return null;
        }
    }

    /**
     * 获取Excel2007图片
     * @param sheetNum 当前sheet编号
     * @param sheet 当前sheet对象
     * @param workbook 工作簿对象
     * @return Map key:图片单元格索引（0_1_1）String，value:图片流PictureData
     */
    public static Map<String, PictureData> getSheetPictrues07(
            int sheetNum, XSSFSheet sheet, XSSFWorkbook workbook) {
        Map<String, PictureData> sheetIndexPicMap = new HashMap<>();

        for (Row row : sheet){
            int end = row.getLastCellNum();
            String v = null;

            School school = new School();
            school.postion = "0_"+ row.getRowNum() +"_0";
            for (int i = 0; i < end; i++) {
                Cell cell = row.getCell(i);
                if (cell == null) {
                    continue;
                }
                Object obj = getValue(cell);
                if (i == 1){
                    //学校中文
                    school.chinese = (String) obj;
                }
                if (i == 2){
                    //学校英文
                    v = (String) obj;
                    v = v.toLowerCase().trim();
                    v = v.replaceAll("\\s+", "_");
                }
            }
            school.englist = v;
            schools.add(school);
        }

        //http://file.mixpay.test/cpa/payee/university_of_bristol.jpeg

        for (POIXMLDocumentPart dr : sheet.getRelations()) {
            if (dr instanceof XSSFDrawing) {
                XSSFDrawing drawing = (XSSFDrawing) dr;
                List<XSSFShape> shapes = drawing.getShapes();
                for (XSSFShape shape : shapes) {
                    XSSFPicture pic = (XSSFPicture) shape;
                    XSSFClientAnchor anchor = pic.getPreferredSize();
                    CTMarker ctMarker = anchor.getFrom();
                    String picIndex = String.valueOf(sheetNum) + "_" + ctMarker.getRow() + "_" + ctMarker.getCol();
                    XSSFPictureData pictureData = pic.getPictureData();
                    School school = getByPosition(picIndex);
                    sheetIndexPicMap.put(school.englist, pictureData);
                    school.pic = pre + school.englist + "." + pictureData.suggestFileExtension();
                }
            }
        }
        return sheetIndexPicMap;
    }

    private static School getByPosition(String position){
        for (School school : schools){
            if (position.equals(school.postion)){
                return school;
            }
        }
        return null;
    }


    private static Object getValue(Cell cell) {
        if (cell==null){
            return null;
        }
        Object obj = null;
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
                obj = cell.getBooleanCellValue();
                break;
            case ERROR:
                obj = cell.getErrorCellValue();
                break;
            case NUMERIC:
                obj = cell.getNumericCellValue();
                break;
            case STRING:
                obj = cell.getStringCellValue();
                break;
            default:
                break;
        }
        return obj;
    }


    public static void printImg(List<Map<String, PictureData>> sheetList) throws IOException {
        for (Map<String, PictureData> map : sheetList) {
            Object key[] = map.keySet().toArray();
            for (int i = 0; i < map.size(); i++) {
                // 获取图片流
                PictureData pic = map.get(key[i]);
                // 获取图片索引
                String picName = key[i].toString();
                // 获取图片格式
                String ext = pic.suggestFileExtension();
                byte[] data = pic.getData();
                FileOutputStream out = new FileOutputStream("D:\\CPA\\pic\\" + picName + "." + ext);
                out.write(data);
                out.close();
            }
        }
    }

}

