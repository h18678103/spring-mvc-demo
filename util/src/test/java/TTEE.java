import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author huqinsong
 * @date 2018/11/6
 */
public class TTEE {
   static String s = "1\t工商银行\ticbc\n" +
            "2\t农业银行\tabc\n" +
            "3\t中国银行\tboc\n" +
            "4\t建设银行\tccb\n" +
            "5\t交通银行\tcomm\n" +
            "6\t招商银行\tcmb\n" +
            "7\t中信银行\tcncb\n" +
            "8\t中国光大银行\tceb\n" +
            "9\t民生银行\tcmsb\n" +
            "10\t兴业银行\tcib\n" +
            "11\t广发银行\tcgb\n" +
            "12\t浦发银行\tspdb\n" +
            "13\t华夏银行\thxb\n" +
            "14\t平安银行\tpab\n" +
            "15\t邮政储蓄银行\tpsbc\n" +
            "16\t恒丰银行\tegb\n" +
            "17\t渤海银行\tcbhb\n" +
            "18\t浙商银行\tzsb\n" +
            "19\t北京银行\tbjb\n" +
            "20\t上海银行\tshb\n" +
            "21\t安徽农信联\tahcu\n" +
            "22\t鞍山商业银行\tascb\n" +
            "23\t东亚银行\tbea\n" +
            "24\t北京顺义银座\tbjsyyzb\n" +
            "25\t包商银行\tbsb\n" +
            "26\t长安银行\tcab\n" +
            "27\t承德银行\tcdb\n" +
            "28\t重庆银行\tcqb\n" +
            "29\t重庆黔江银座\tcqqjyzb\n" +
            "30\t重庆农村商业银行\tcqrcb\n" +
            "31\t重庆渝北银座\tcqybyzb\n" +
            "32\t长沙银行\tcsb\n" +
            "33\t沧州银行\tczb\n" +
            "34\t东莞银行\tdgb\n" +
            "35\t东莞农商行\tdgrb\n" +
            "36\t大连银行\tdlb\n" +
            "37\t德阳银行\tdyb\n" +
            "38\t东营商业银行\tdycb\n" +
            "39\t德州银行\tdzb\n" +
            "40\t福建农信联\tfjcu\n" +
            "41\t福建海峡银行\tfjhxb\n" +
            "42\t佛山顺德农商行\tfssdrb\n" +
            "43\t阜新银行\tfxb\n" +
            "44\t广东华兴银行\tgdhxb\n" +
            "45\t广东南粤银行\tgdnyb\n" +
            "46\t桂林银行\tglb\n" +
            "47\t广西北部湾银行\tgxbbwb\n" +
            "48\t柳州银行\tgxlzb\n" +
            "49\t广西农信联\tgxzzcu\n" +
            "50\t贵阳商业银行\tgycb\n" +
            "51\t广州银行\tgzb\n" +
            "52\t广州农商行\tgzrb\n" +
            "53\t韩亚银行\thanab\n" +
            "54\t哈尔滨银行\tharbb\n" +
            "55\t河北银行\thbb\n" +
            "56\t湖北农信联\thbcu\n" +
            "57\t邯郸商业银行\thdcb\n" +
            "58\t汉口银行\thkb\n" +
            "59\t葫芦岛银行\thldb\n" +
            "60\t海南农信联\thncu\n" +
            "61\t徽商银行\thsb\n" +
            "62\t湖州银行\thuzb\n" +
            "63\t杭州银行\thzb\n" +
            "64\t晋城银行\tjcb\n" +
            "65\t吉林银行\tjlb\n" +
            "66\t吉林农信联\tjlcu\n" +
            "67\t济宁银行\tjnb\n" +
            "68\t江苏银行\tjsb\n" +
            "69\t江苏常熟农商行\tjscsrb\n" +
            "70\t江苏农信联\tjscu\n" +
            "71\t嘉兴银行\tjxb\n" +
            "72\t赣州银行\tjxgzb\n" +
            "73\t江西赣州银座\tjxgzyzb\n" +
            "74\t锦州银行\tjzb\n" +
            "75\t外换银行\tkebcn\n" +
            "76\t开封商业银行\tkfcb\n" +
            "77\t昆仑银行\tklb\n" +
            "78\t富滇银行\tkmcb\n" +
            "79\t昆山农商行\tksrb\n" +
            "80\t廊坊银行\tlfb\n" +
            "81\t漯河商业银行\tlhcb\n" +
            "82\t龙江银行\tljb\n" +
            "83\t莱商银行\tlsb\n" +
            "84\t洛阳银行\tlyb\n" +
            "85\t临商银行\tlycb\n" +
            "86\t兰州银行\tlzb\n" +
            "87\t绵阳商业银行\tmycb\n" +
            "88\t宁波银行\tnbcb\n" +
            "89\t宁波鄞州农行\tnbyzrb\n" +
            "90\t南昌银行\tncb\n" +
            "91\t南京市商业银行\tnjcb\n" +
            "92\t内蒙古银行\tnmgb\n" +
            "93\t农信银中心\tnongxinyin\n" +
            "94\t宁夏银行\tnxb\n" +
            "95\t宁夏黄河农商行\tnxhhrb\n" +
            "96\t农村信用合作社\tnxs\n" +
            "97\t南阳银行\tnyb\n" +
            "98\t鄂尔多斯银行\tordb\n" +
            "99\t攀枝花商业银行\tpzhcb\n" +
            "100\t青岛银行\tqdb\n" +
            "101\t青海银行\tqhb\n" +
            "102\t齐鲁银行\tqlb\n" +
            "103\t齐商银行\tqsb\n" +
            "104\t泉州银行\tqzb\n" +
            "105\t北京农商行\trcb\n" +
            "106\t日照银行\trzb\n" +
            "107\t山东农信联\tsdcu\n" +
            "108\t新韩银行\tshinhanb\n" +
            "109\t上海农商行\tshrb\n" +
            "110\t商丘商业银行\tsqcb\n" +
            "111\t上饶银行\tsrb\n" +
            "112\t绍兴银行\tsxb\n" +
            "113\t晋商银行\tsxjsb\n" +
            "114\t苏州银行\tszb\n" +
            "115\t深圳福田银座\tszftyzb\n" +
            "116\t深圳农商行\tszrb\n" +
            "117\t泰安商业银行\ttacb\n" +
            "118\t天津银行\ttjb\n" +
            "119\t天津农村银行\ttjrb\n" +
            "120\t台州银行\ttzb\n" +
            "121\t潍坊银行\twfb\n" +
            "122\t威海商业银行\twhcb\n" +
            "123\t吴江农商行\twjrb\n" +
            "124\t乌鲁木齐商业银行\twlmqcb\n" +
            "125\t友利银行\twobcn\n" +
            "126\t温州银行\twzb\n" +
            "127\t厦门银行\txmb\n" +
            "128\t邢台银行\txtb\n" +
            "129\t营口银行\tykb\n" +
            "130\t云南农信联\tyncu\n" +
            "131\t烟台银行\tytb\n" +
            "132\t自贡商业银行\tzgcb\n" +
            "133\t珠海华润银行\tzhhrb\n" +
            "134\t浙江农信联\tzjcu\n" +
            "135\t浙江稠州银行\tzjczcb\n" +
            "136\t张家港农商行\tzjgrb\n" +
            "137\t浙江景宁银座\tzjjnyzb\n" +
            "138\t张家口商业银行\tzjkcb\n" +
            "139\t浙江民泰银行\tzjmtcb\n" +
            "140\t浙江三门银座\tzjsmyzb\n" +
            "141\t浙江泰隆银行\tzjtlcb\n";


    static String s2 = "BOC,中国银行,104100004933.ABC,农业银行,103100004425.CCB,建设银行,105100004067.ICBC,工商银行,102100000353.BCM,交通银行,301100000187.CMB,招商银行," +
           "308100005310.CEB,光大银行,303100000330.PINGAN,平安银行,307100003205.PSBC,邮政储蓄银行,403100000359.CITIC,中信银行,302100011325.CGB,广发银行,306100005071.BOB,北京银行,313584001064.CMBC,民生银行,305100001442.HXB,华夏银行,304100002039.CIB,兴业银行,309100001524.SPDB,上海浦东发展银行,310100000331.SHB,上海银行,313584005041.SRCB,上海农商银行,322290001071.NBCB,宁波银行,313584003046.NJCB,南京银行,313100014074.HKBEA,东亚银行,502100004217.CBHB,渤海银行,318100010073.BOCD,成都银行,313651002015.HSBANK,徽商银行,319301000011.TCCB,天津银行,313100010055.BJRCB,北京农商银行,402100000067.SDB,深圳发展银行,307100003019.CZBANK,浙商银行,316100000033.QLBCHINA,齐鲁银行,313451007641.HZBANK,杭州银行,313584004006.LZBANK,兰州银行,313821016035.JX-BANK,江西银行,000000.QDCCB,青岛银行,313452502617.GUILINBANK,桂林银行,313622500025.GRCBANK,广州农村商业银行,314581000011.BGZCHINA,贵州银行,313701099012.YBNSYH,延边农村商业银行,000000.FYYHBANK,颖淮农商行,000000.HCNSH,珲春农商银行,000000.CQRCB,重庆农商银行,000000.HFBANK,恒丰银行,000000.XMCCB,厦门银行,000000.SXNXS,陕西信合银行,000000.CZCB,浙江稠州银行,000000";
    public static void main(String[] args) {
        String[] split = s.split("\n");
        List<Bank> bankList = new LinkedList();
        for (String s : split){
            String[] split1 = s.split("\t");
            Bank bank = new Bank();
            bank.setNo(split1[0]);
            bank.setName(split1[1]);
            bank.setEn(split1[2]);
            bankList.add(bank);
        }

        String[] split2 = s2.split("\\.");
        List<Bank> bankList2 = new LinkedList();
        for (String s : split2){
            String[] split1 = s.split(",");
            Bank bank = new Bank();
            bank.setEn(split1[0]);
            bank.setName(split1[1]);
            bank.setNo(split1[2]);
            bankList2.add(bank);
        }

        for (Bank bank : bankList){
            System.out.println(bank);
        }
        System.out.println("========================================================================");
        for (Bank bank : bankList2){
            System.out.println(bank);
        }
        System.out.println("========================================================================");
        for (Bank bank : bankList){
            for (Bank bank2 : bankList2){
                if (bank.getName().equals(bank2.getName()) || bank.getEn().equalsIgnoreCase(bank2.getEn())){
                    //<!-- 工商银行 -->
                    //<entry><key>CGB</key><value>306</value></entry>
                    System.out.println("<!-- "+bank2.getName()+" -->");
                    System.out.println("<entry><key>"+bank2.getEn()+"</key><value>"+bank.getNo()+"</value></entry>");
                }
            }
        }
    }

    static class Bank{
        String no;
        String name;
        String en;

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEn() {
            return en;
        }

        public void setEn(String en) {
            this.en = en;
        }

        @Override
        public String toString() {
            return "Bank{" +
                    "no='" + no + '\'' +
                    ", name='" + name + '\'' +
                    ", en='" + en + '\'' +
                    '}';
        }
    }
}
