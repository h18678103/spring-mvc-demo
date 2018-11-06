package array;

/**
 * @author 吴鹏
 * @description
 * @Email shusheng@yiji.com
 * @date 2018/10/10 16:37
 */
public class TaxCalculationTest {

    public static void main(String[] args) {
        /**money为工资扣除五险一金之后*/
        double money = 8888, wuXianYiJin = 0;

        System.out.println(jisuanOld(money));
        System.out.println(jisuanNew(money));
        System.out.println(jisuanNew(money) / jisuanOld(money) * 100);
        System.out.println(jisuanOld(money) - jisuanNew(money));

    }

    private static double jisuanOld(double money) {
        double kouShui = 0;
        double amount = money - 3500;

        if (amount <= 1500) {
            kouShui = amount * 0.03;
        } else if (1500 < amount && amount <= 4500) {
            kouShui = amount * 0.1 - 105;
        } else if (4500 < amount && amount <= 9000) {
            kouShui = amount * 0.2 - 555;
        } else if (9000 < amount && amount <= 35000) {
            kouShui = amount * 0.25 - 1005;
        }

        return kouShui;
    }

    private static double jisuanNew(double money) {
        double kouShui = 0;
        double amount = money - 5000;

        if (amount <= 3000) {
            kouShui = amount * 0.03;
        } else if (1500 < amount && amount <= 12000) {
            kouShui = amount * 0.1 - 210;
        } else if (12000 < amount && amount <= 25000) {
            kouShui = amount * 0.2 - 1410;
        } else if (25000 < amount && amount <= 35000) {
            kouShui = amount * 0.25 - 2660;
        }

        return kouShui;
    }

}