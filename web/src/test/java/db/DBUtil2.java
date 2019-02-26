package db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author huqinsong
 * @date 2019/2/25
 */
public class DBUtil2 {
    //这里可以设置数据库名称
//    private final static String URL = "jdbc:sqlserver://192.168.1.198:1433;DatabaseName=ForeignCurrency";
    private final static String URL = "jdbc:sqlserver://192.168.1.198:1433;DatabaseName=ForeignPayment";
    private static final String USER = "sa";
    private static final String PASSWORD = "Time.2017";

    private static Connection conn = null;


    public static void main(String[] args) throws Exception {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet resultSet = metaData.getTables(null, "%", "%", new String[] { "TABLE" });
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                System.out.println(tableName+"++++++++++++++++++++++++++++++++++++++++++++");
                ResultSet resultCol = metaData.getColumns(null, "%", tableName, "%");
                while (resultCol.next()) {
                    String columnName = resultCol.getString("COLUMN_NAME");
                    String columnType = resultCol.getString("TYPE_NAME");
                    int datasize = resultCol.getInt("COLUMN_SIZE");
                    int digits = resultCol.getInt("DECIMAL_DIGITS");
                    int nullable = resultCol.getInt("NULLABLE");
                    String REMARKS = resultCol.getString("REMARKS");
                    System.out.println(columnName + " " + columnType + " " + datasize + " " + digits + " " + nullable+ " " + REMARKS);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
