package excel;

/**
 * @author huqinsong
 * @date 2018/8/22
 */
public class C {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("INSERT INTO country (cn_name, abbr) VALUES ");
        String s = "阿尔巴尼亚共和国 ALB 阿尔及利亚 DZA 阿富汗伊斯兰国 AFG 阿根廷共和国 ARG 阿联酋 ARE 阿鲁巴 ABW 阿曼苏丹国 OMN 阿塞拜疆共和国 AZE 埃及 EGY 埃塞俄比亚 ETH 爱尔兰 IRL 爱沙尼亚共和国 EST 安道尔公国 AND 安哥拉共和国 AGO 安圭拉 AIA 安提瓜和巴布达 ATG 奥地利共和国 AUT 澳大利亚联邦 AUS 澳门 MAC 巴巴多斯 BRB 巴布亚新几内亚 PNG 巴哈马联邦 BHS 巴基斯坦 PAK 巴拉圭共和国 PRY 巴勒斯坦国 PSE 巴林国 BHR 巴拿马共和国 PAN 巴西联邦共和国 BRA 白俄罗斯共和国 BLR 百慕大群岛 BMU 保加利亚共和国 BGR 北马里亚纳 MNP 贝劳共和国 PLW 贝宁共和国 BEN 比利时王国 BEL 冰岛共和国 ISL 波多黎各自由联邦 PRI 波黑共和国 BIH 波兰共和国 POL 玻利维亚共和国 BOL 伯利兹 BLZ 博茨瓦纳共和国 BWA 不丹王国 BTN 布基纳法索 BFA 布隆迪共和国 BDI 布维岛 BVT 朝鲜 PRK 赤道几内亚共和国 GNQ 丹麦王国 DNK 德国 DEU 东帝汶 TMP 多哥共和国 TGO 多米尼加共和国 DOM 多米尼克联邦 DMA 俄罗斯联邦 RUS 厄瓜多尔共和国 ECU 厄立特里亚国 ERI 法国 FRA 法罗群岛 FRO 法属波利尼西亚 PYF 法属圭亚那 GUF 法属南部领土 ATF 梵蒂冈城国 VAT 菲律宾共和国 PHL 斐济共和国 FJI 芬兰共和国 FIN 佛得角共和国 CPV 冈比亚共和国 GMB 刚果共和国 COG 哥伦比亚共和国 COL 哥斯达黎加共和国 CRI 格林纳达 GRD 格陵兰 GRL 格鲁吉亚共和国 GEO 古巴共和国 CUB 瓜德罗普 GLP 关岛 GUM 圭亚那合作共和国 GUY ";
        s += "哈萨克斯坦共和国 KAZ 海地共和国 HTI 韩国 KOR 荷兰王国 NLD 荷属安的列斯 ANT 赫德岛和麦克唐纳 HMD 洪都拉斯共和国 HND 基里巴斯共和国 KIR 吉布提共和国 DJI 吉尔吉斯共和国 KGZ 几内亚比绍 GNB 几内亚共和国 GIN 加拿大 CAN 加纳共和国 GHA 加蓬共和国 GAB 柬埔寨王国 KHM 捷克共和国 CZE 津巴布韦共和国 ZWE 喀麦隆共和国 CMR 卡塔尔国 QAT 开曼群岛 CYM 科科斯(基林)群岛 CCK 科摩罗共和国 COM 科特迪瓦共和国 CIV 科威特国 KWT 克罗地亚共和国 HRV 肯尼亚共和国 KEN 库克群岛 COK 拉脱维亚共和国 LVA 莱索托王国 LSO 老挝 LAO 黎巴嫩共和国 LBN 立陶宛共和国 LTU 利比里亚共和国 LBR 利比亚 LBY 列支敦士登公国 LIE 留尼汪 REU 卢森堡大公国 LUX 卢旺达共和国 RWA 罗马尼亚 ROM 马达加斯加共和国 MDG 马尔代夫共和国 MDV 马尔维纳斯群岛 FLK 马耳他共和国 MLT 马拉维共和国 MWI 马来西亚 MYS 马里共和国 MLI 马其顿共和国 MKD 马绍尔群岛共和国 MHL 马提尼克 MTQ 马约特 MYT 毛里求斯共和国 MUS 毛里塔尼亚 MRT 美国 USA 美属萨摩亚 ASM 美属太平洋各群岛 UMI 美属维尔京群岛 VIR 蒙古国 MNG 蒙特塞拉特 MSR 孟加拉人民共和国 BGD 秘鲁共和国 PER 密克罗尼西亚联邦 FSM 缅甸联邦 MMR 摩尔多瓦共和国 MDA 摩洛哥王国 MAR 摩纳哥公国 MCO 莫桑比克共和国 MOZ 墨西哥合众国 MEX 纳米比亚共和国 NAM 南非共和国 ZAF 南极洲 ATA 南乔治南桑德韦奇 SGS 塞尔维亚 SRB 瑙鲁共和国 NRU 尼伯尔王国 NPL 尼加拉瓜共和国 NIC 尼日尔共和国 NER 尼日利亚 NGA 纽埃 NIU 挪威王国 NOR 诺福克岛 NFK 皮特凯恩岛 PCN 葡萄牙共和国 PRT 日本国 JPN 瑞典王国 SWE 瑞士联邦 CHE ";
        s += "萨尔多瓦共和国 SLV 塞拉利昂共和国 SLE 塞内加尔共和国 SEN 塞浦路斯共和国 CYP 塞舌尔共和国 SYC 沙特阿拉伯王国 SAU 圣诞岛 CSR 圣多美和普林西比 STP 圣赫勒拿 SHN 圣基茨和尼维斯 KNA 圣卢西亚 LCA 圣马力诺共和国 SMR 圣皮埃尔和密克隆 SPM 圣文森特 VCT 斯里兰卡 LKA 斯洛伐克共和国 SVK 斯洛文尼亚共和国 SVN 斯瓦尔巴群岛 SJM 斯威士兰王国 SWZ 苏丹共和国 SDN 苏里南共和国 SUR 所罗门群岛 SLB 索马里共和国 SOM 塔吉克斯坦共和国 TJK 泰王国 THA 坦桑尼亚 TZA 汤加王国 TON 特克斯和凯科斯 TCA 特立尼达和多巴哥 TTO 突尼斯共和国 TUN 图瓦卢 TUV 土耳其共和国 TUR 土库曼斯坦 TKM 托克劳 TKL 瓦利斯和富图纳 WLF 瓦努阿图共和国 VUT 危地马拉共和国 GTM 委内瑞拉共和国 VEN 文莱达鲁萨兰国 BRN 乌干达共和国 UGA 乌克兰 UKR 乌拉圭东岸共和国 URY 乌兹别克斯坦 UZB 西班牙 ESP 西撒哈拉 ESH 西萨摩亚独立国 WSM 希腊共和国 GRC 香港 HKG 新加坡共和国 SGP 新喀里多尼亚 NCL 新西兰 NZL 匈牙利共和国 HUN 叙利亚 SYR 牙买加 JAM 亚美尼亚共和国 ARM 也门共和国 YEM 伊拉克共和国 IRQ 伊朗伊斯兰共和国 IRN 以色列国 ISR 意大利共和国 ITA 印度共和国 IND 印度尼西亚共和国 IDN 英国 GBR 英属维尔京群岛 VGB 英属印度洋领土 IOT 约旦哈希姆王国 JOR 越南 VNM 赞比亚共和国 ZMB 扎伊尔共和国 ZAR 乍得共和国 TCD 直布罗陀 GIB 智利共和国 CHL 中非共和国 CAF 中国 CHN 中国台湾 TWN 刚果（金） COD 黑山 MNE 塞尔维亚和黑山 SCG ";
        String[] split = s.split(" ");

        for (int i = 0; i<split.length;){
            sb.append("('").append(split[i]).append("', '");
            i++;
            sb.append(split[i]).append("'), ");
            i++;
        }
        System.out.println(sb.toString());
    }
}
