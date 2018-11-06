package timer;

import java.util.ArrayList;
import java.lang.Math;
import java.lang.Double;

public class love_num {
    public static void main(String[] args) {
        int intMain = 2;
        int intBig = 10000;
        while (true) {
            ArrayList<Integer> listYakuSu1 = findYakuSu(intMain);
            int intSum1 = addYakuSu(listYakuSu1);
            if (intSum1 == intMain) {
                System.out.println("self num:" + intSum1);
                System.out.println("list="+listYakuSu1);
                System.out.println("intSum1="+intSum1);
            } else {
                ArrayList<Integer> listYakuSu2 = findYakuSu(intSum1);
                int intSum2 = addYakuSu(listYakuSu2);
                if (intSum2 == intMain) {
                    System.out.println("love num:" + intMain + "--" + intSum1);
                    System.out.println("list1="+listYakuSu1);
                    System.out.println("list2="+listYakuSu2);
                    System.out.println("intSum1="+intSum1);
                    System.out.println("intSum2="+intSum2);
                }
            }
            intMain++;
            if (intMain > intBig) {
                return;
            }
        }
    }

    public static int addYakuSu(ArrayList<Integer> listYakuSu) {
        int sum = 0;
        for (int i = 0; i < listYakuSu.size(); i++) {
            sum += listYakuSu.get(i);
        }
        return sum;
    }

    public static ArrayList<Integer> findYakuSu(int intNum) {
        ArrayList<Integer> listYakuSu = new ArrayList();
        double dbNum = (double) intNum;
        double dbRoot = Math.sqrt(dbNum);
        Double d = new Double(dbRoot);
        int intRoot = d.intValue();
        listYakuSu.add(1);
        for (int i = 2; i <= intRoot; i++) {
            int intPart = intNum / i;
            if (intPart * i == intNum) {
                if (intPart == i) {
                    listYakuSu.add(i);
                } else {
                    listYakuSu.add(i);
                    listYakuSu.add(intPart);
                }
            }
        }
        return listYakuSu;
    }
}