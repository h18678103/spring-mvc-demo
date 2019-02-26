package db;

import java.sql.*;

/**
 * @author huqinsong
 * @date 2019/2/25
 */
public class DBUtil {
    //这里可以设置数据库名称
//    private final static String URL = "jdbc:sqlserver://192.168.1.198:1433;DatabaseName=ForeignCurrency";
    private final static String URL = "jdbc:sqlserver://192.168.1.198:1433;DatabaseName=ForeignPayment";
    private static final String USER = "sa";
    private static final String PASSWORD = "Time.2017";

    private static Connection conn = null;

    //静态代码块（将加载驱动、连接数据库放入静态块中）
    static {
        try {
            //1.加载驱动程序
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //2.获得数据库的连接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //测试用例
    public static void main(String[] args) throws Exception {

        //3.通过数据库的连接操作数据库，实现增删改查
        Statement stmt = conn.createStatement();
        //ResultSet executeQuery(String sqlString)：执行查询数据库的SQL语句   ，返回一个结果集（ResultSet）对象。
        ResultSet rs = stmt.executeQuery("SELECT payId,tradeType,merchantId FROM [tds].[pay_order]");
//        while (rs.next()) {//如果对象中有数据，就会循环打印出来
//            System.out.println(rs.getLong("payId") + "," + rs.getString("tradeType") + "," + rs.getInt("merchantId"));
//        }

        ResultSetMetaData metaData = rs.getMetaData();
        String columnName = metaData.getColumnName(2);
        System.out.println(columnName);
        String schemaName = metaData.getTableName(2);
        System.out.println(schemaName);
        String catalogName = metaData.getColumnTypeName(2);
        System.out.println(catalogName);
    }
}
