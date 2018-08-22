package jdbc;

import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

/**
 * @author huqinsong
 * @date 2018/7/16
 */
public class DBAdd {

    static String sql = "update a set count = count + 1 where id = 2";//SQL语句
    static DBHelper db1 = new DBHelper(sql);//创建DBHelper对象

    public static void add() {
        try {
            db1.pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static CountDownLatch countDownLatch = new CountDownLatch(3){
        @Override
        public void await() throws InterruptedException {
            System.out.println(Thread.currentThread().getName() +  " count down start");
            super.await();
            System.out.println(Thread.currentThread().getName() +  " count down end");
            db1.close();
            System.out.println(Thread.currentThread().getName() +  " count down db close");
        }
    };

    public static void main(String[] args) {
        add();
    }

}
