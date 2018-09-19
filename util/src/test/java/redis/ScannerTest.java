package redis;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author huqinsong
 * @date 2018/9/19
 */
public class ScannerTest {

    public static void main(String args[]) throws IOException {
        t2();
    }

    private static void t1() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你的姓名：");
        String name = sc.nextLine();
        System.out.println("请输入你的年龄：");
        int age = sc.nextInt();
        System.out.println("请输入你的工资：");
        float salary = sc.nextFloat();
        System.out.println("你的信息如下：");
        System.out.println("姓名：" + name + "\n" + "年龄：" + age + "\n" + "工资：" + salary);
    }
    private static void t2() throws IOException {
        System.out.print("请输入：");
        char i = (char) System.in.read();
        System.out.println(" aaa:" + i);

    }
}
