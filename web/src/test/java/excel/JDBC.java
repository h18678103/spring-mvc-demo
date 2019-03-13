package excel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author huqinsong
 * @date 2019/3/13
 */
public class JDBC {

    // 不同的数据库有不同的驱动
    String driverName = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/test";
    String user = "root";
    String password = "123456";
    Connection conn;
    PreparedStatement ps;

    public void initDB(){
        String sql = "INSERT INTO `area_boc` (`code`, `num`, `short`, `full`) VALUES (?, ?, ?, ?)";
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, user, password);
            ps = conn.prepareStatement(sql);
            System.out.println("db initialized!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void destroyDB(){
        close(conn);
        close(ps);
        System.out.println("db destroyed!");
    }

    public void close(AutoCloseable autoCloseable){
        try {
            autoCloseable.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void insert(String code, String num, String st, String full) throws SQLException {
        if (ps==null){
            System.out.println("not init db!");
            return;
        }
        ps.setString(1, code);
        ps.setString(2, num);
        ps.setString(3, st);
        ps.setString(4, full);
        int i = ps.executeUpdate();
        System.out.println(st+"=>插入结果:" + i);
    }


}
