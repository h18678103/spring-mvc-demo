package com.hqs.test.web.pojo;

import com.alibaba.fastjson.JSON;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基础工具
 * @author devin.xu
 * @date 2016.06
 */
public final class BaseUtil {

    private static final Logger log = LoggerFactory.getLogger(BaseUtil.class);

    /** RANDOM */
    private static final Random RANDOM = new Random();

    /** 100 */
    private static final BigDecimal DEC100 = new BigDecimal(100);

    /** 金额格式化，用于移除小数点后的无效0 */
    private static final DecimalFormat CENT_ZERO_TRIMMER = new DecimalFormat( "###################.##" );

    /*----------------------------------------
     *                字符串接口
     *-----------------------------------------*/
    /**
     * 将字符串截取指定长度
     * @param o obj
     * @return o == null ? "" : o.toString()
     */
    public static String toStr(Object o) {
        return o == null ? "" : o.toString();
    }


    /**
     * 将字符串截取指定长度
     * @param s 字符串
     * @param len 长度
     * @return 截取后的新串
     */
    public static String strCut(String s, int len) {
        return (isBlank(s) || s.length() <= len) ? s : StringUtils.substring(s, 0, len);
    }

    /**
     * 拼接成字符串
     * @param arr 数组
     * @param separator 分隔符
     * @return 按分隔符拼接后的字符串
     */
    public static String join(Object[] arr, String separator) {
        return StringUtils.join(arr, separator);
    }

    /**
     * 拼接字符串，默认按','拼接
     * @param arr 数组
     * @return 拼接后的字符串
     */
    public static String join(Object[] arr) {
        return join(arr, ",");
    }

    /**
     * 检查字符串是否为空
     * @param s 字符串
     * @return yes|no
     */
    public static boolean isBlank(String s) {
        return StringUtils.isBlank(s);
    }

    /**
     * 检查数值是否为null或0
     * @param num num
     * @return yes|no
     */
    public static boolean isBlank(Number num) {
        return num == null || num.intValue() == 0;
    }

    /**
     * 判断非空
     * @param s str
     * @return yes|no
     */
    public static boolean notBlank(String s) {
        return StringUtils.isNotBlank(s);
    }

    /**
     * 判断非空
     * @param num num
     * @return yes|no
     */
    public static boolean notBlank(Number num) {
        return !isBlank(num);
    }

    /**
     * RANDOM
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().toUpperCase();
    }

    /**
     * 生成指定长度的随机字符串
     * @param length 字符串长度
     * @param baseChars 字符范围
     * @return randomstr
     */
    public static String getRandomString(final int length, final String baseChars) {
        char[] values = new char[length];
        for (int i = 0; i < length; i++) {
            values[i] = baseChars.charAt(RANDOM.nextInt(baseChars.length()));
        }

        return new String(values);
    }


    /**
     * 生成指定长度的随机字符串
     */
    public static String getRandomString(int length) {
        return getRandomString(length, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
    }

    /**
     * 生成指定长度的随机字数字串
     */
    public static String getRandomNumStr(int length) {
        return getRandomString(length, "0123456789");
    }

    /**
     * 生成指定范围的随机数
     * @param bound the upper bound (exclusive).  Must be positive.
     */
    public static int nextInt(int bound) {
        return RANDOM.nextInt(bound);
    }

    /**
     * 字符串首字母转大写
     * @param s 要转为大写的字符串
     * @return 首字母大写后的字符串
     */
    public static String firstUpper(String s) {
        if (isBlank(s) || s.charAt(0) < 'a' || s.charAt(0) > 'z') {
            return s;
        }

        char[] cs = s.toCharArray();
        cs[0] -= 32;
        return new String(cs);
    }

    /**
     * 将Byte-Set 转成String-Set
     * @param keySet keySet
     * @return set
     */
    public static Set<String> toStrSet(Set<byte[]> keySet) {
        if (BaseUtil.notEmpty(keySet)) {
            Set<String> set = new HashSet<>(keySet.size());
            keySet.forEach(arr -> set.add(new String(arr)));
            return set;
        }

        return null;
    }

    /**----------------------------------------
     *                容器接口
     *-----------------------------------------*/
    public static boolean isEmpty(Collection<?> c) {
        return null == c || 0 == c.size();
    }
    public static boolean notEmpty(Collection<?> c) {
        return !isEmpty(c);
    }
    public static int getSize(Collection<?> c) {
        return isEmpty(c) ? 0 : c.size();
    }

    public static boolean isEmpty(Map<?, ?> m) {
        return null == m || 0 == m.size();
    }
    public static boolean notEmpty(Map<?, ?> m) {
        return !isEmpty(m);
    }
    public static int getSize(Map<?, ?> m) {
        return isEmpty(m) ? 0 : m.size();
    }

    public static <T> boolean isEmpty(T[] arr) {
        return null == arr || 0 == arr.length;
    }
    public static <T> boolean notEmpty(T[] arr) {
        return !isEmpty(arr);
    }

    public static <T> boolean isEmpty(byte[] arr) {
        return null == arr || 0 == arr.length;
    }
    public static <T> boolean notEmpty(byte[] arr) {
        return !isEmpty(arr);
    }

    public static <T> boolean contains(Collection<T> c, T element) {
        return notEmpty(c) && c.contains(element);
    }

    /**
     * 将list拆分成指定大小的多个list
     * @param resList 要拆分的list
     * @param newSize 拆分后每个list的大小
     * @return 拆分后的list列表
     */
    public static <T> List<List<T>> splitList(List<T> resList, final int newSize) {
        if (isEmpty(resList) || newSize < 1) {
            return null;
        }

        // 拆分后的结果
        List<List<T>> ret = new ArrayList<>();

        // 数量不足
        int size = resList.size();
        if (size <= newSize) {
            ret.add(resList);
            return ret;
        }

        final int pre = size / newSize;
        final int last = size % newSize;

        // 前面pre个集合，每个大小都是newSize个元素
        for (int i = 0; i < pre; i++) {
            List<T> itemList = new ArrayList<>(newSize);
            for (int j = 0; j < newSize; j++) {
                itemList.add(resList.get(i * newSize + j));
            }

            ret.add(itemList);
        }

        // 处理last
        if (last > 0) {
            List<T> itemList = new ArrayList<>(last);
            final int lastStart = pre * newSize;
            for (int i = 0; i < last; i++) {
                itemList.add(resList.get(lastStart + i));
            }

            ret.add(itemList);
        }

        return ret;
    }

    /**----------------------------------------
     *                容器初始化接口
     *-----------------------------------------*/
    @SuppressWarnings("unchecked")
    private static <T extends Map, E> T fillMap(T map, E[][] kvArr) {
        for (E[] arr : kvArr) {
            map.put(arr[0], arr[1]);
        }

        return map;
    }

    /**
     * 根据数组生成Hashmap
     * @param kvArr kv数组
     * @return HashMap
     */
    public static <E> HashMap<E, E> newHashMap(E[][] kvArr) {
        return fillMap(new HashMap<>(kvArr.length), kvArr);
    }

    /**
     * 根据数组生成LinkedHashMap
     * @param kvArr kv数组
     * @return LinkedHashMap
     */
    public static <E> LinkedHashMap<E, E> newLinkedHashMap(E[][] kvArr) {
        return fillMap(new LinkedHashMap<>(kvArr.length), kvArr);
    }

    /**
     * 根据数组生成Set
     * @param arr 对象数组
     * @return HashSet
     */
    public static <E> HashSet<E> newHashSet(E[] arr) {
        HashSet<E> set = new HashSet<>(arr.length);
        Collections.addAll(set, arr);
        return set;
    }

    /**
     * 根据数组生成Set
     * @param arr 对象数组
     * @return HashSet
     */
    public static <E> HashSet<E> newLinkedHashSet(E[] arr) {
        HashSet<E> set = new LinkedHashSet<>(arr.length);
        Collections.addAll(set, arr);
        return set;
    }


    /**
     * 根据retCode,retMsg生成string map
     *
     * @param retCode 返回码
     * @param retMsg 返回描述
     * @return map
     */
    public static Map<String, Object> toMap(String retCode, String retMsg) {
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("retCode", retCode);
        retMap.put("retMsg", retMsg);
        return retMap;
    }

    /**
     * 对象转json
     * @param obj 要转换的对象
     * @return json
     */
    public static String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * 以json格式从控制台打印一个对象
     * @param obj 要打印的对象
     */
    public static void jsonp(Object obj) {
        String s = (obj == null ? "null" : (obj instanceof String ? (String)obj : toJson(obj)));
        System.out.println(s);
    }

    /**
     * json转对象
     * @param json  json文本
     * @param clazz 解析的类
     * @return 对象
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    /**
     * json字符串转map
     * @param json json字符串
     * @return map
     */
    public static Map<String, Object> jsonToMap(String json) {
        return JSON.parseObject(json);
    }

    /**
     * json字符串转map
     * @param json json字符串
     * @return 列表
     */
    public static List<Object> jsonToArray(String json) {
        return JSON.parseArray(json);
    }

    /**
     * 将xml节点内的子元素转为map，包括空值
     * @param elem xml节点
     * @param sort 是否使用TreeMap排序，不排序时保持元素的默认顺序
     * @return map
     */
    public static Map<String, String> xmlToMap(Element elem, boolean sort) {
        Map<String, String> map = (sort ? new TreeMap<>() : new LinkedHashMap<>());
        if (null == elem) {
            return map;
        }

        Element e = null;
        for (Object child : elem.elements()) {
            e = (Element)child;
            map.put(e.getName(), e.getTextTrim());
        }

        return map;
    }


    /**
     * 序列化
     */
    public static byte[] serialize(Serializable obj) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);) {
            oos.writeObject(obj);
            return bos.toByteArray();
        } catch (Exception e) {
            log.error("serialize失败", e);
            return null;
        }
    }

    /**
     * 反序列化
     */
    public static Object deserialize(byte[] bytes) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);) {
            return ois.readObject();
        } catch (Exception e) {
            log.error("deserialize失败", e);
            return null;
        }
    }

    /**
     * 数字字符串转数字数组
     *
     * @param s
     * @return
     */
    public static int[] str2IntArr(String s) {
        if (isBlank(s)) {
            return null;
        }

        int[] intArr = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            intArr[i] = s.charAt(i) - '0';
        }
        return intArr;
    }

    public static long ipToLong(String ipAddress) {
        long result = 0, ip = 0;
        String[] ipAddressInArray = ipAddress.split("\\.");
        for (int i = 3; i >= 0; i--) {
            ip = Long.parseLong(ipAddressInArray[3 - i]);
            result |= ip << (i * 8);
        }
        return result;
    }

    public static String longToIp(long ip) {
        return ((ip >> 24) & 0xFF) + "." + ((ip >> 16) & 0xFF)
                + "." + ((ip >> 8) & 0xFF) + "." + (ip & 0xFF);
    }

    /**
     * 驼峰转下划线
     * @param param
     * @return
     */
    public static String camelToUnderline(String param){
        if (isBlank(param)) {
            return "";
        }

        int len = param.length();
        char c = 0;
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            c=param.charAt(i);
            if (Character.isUpperCase(c)){
                sb.append("_").append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**----------------------------------------
     *                时间接口
     *-----------------------------------------*/
    public static String formatTime(Date date) {
        return DateFormatUtils.format(date, "HH:mm:ss");
    }

    /**----------------------------------------
     *                日期接口
     *-----------------------------------------*/
    public static String formatDateDay(Date date) {
        return DateFormatUtils.format(date, "yyyyMMdd");
    }

    public static String formatDateTime(Date date) {
        return DateFormatUtils.format(date, "yyyyMMddHHmmss");
    }

    public static String formatDateTimeMillis(Date date) {
        return DateFormatUtils.format(date, "yyyyMMddHHmmssSSS");
    }

    public static String formatDate(Date date, String format) {
        return DateFormatUtils.format(date, format);
    }

    public static String getFormatDateDay() {
        return formatDateDay(new Date());
    }

    public static String getFormatDateTime() {
        return formatDateTime(new Date());
    }

    public static String getFormatDateTimeMillis() {
        return formatDateTimeMillis(new Date());
    }

    public static String getFormatDate(String format) {
        return formatDate(new Date(), format);
    }

    public static Date parseDate(String dateStr, String format) {
        try {
            return notBlank(dateStr) ? DateUtils.parseDate(dateStr, format) : null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取当前时间并将毫秒数设置为0-用于将精度控制到秒，避免mysql的TIMESTAMP不保存毫秒导致精度问题
     * @return 当前时间
     */
    public static Date getDateWithMillisecondZero() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取当前时间并将时分秒设置为0-用于只取天的场景
     * @param date 日期
     * @return 该日期当日0点的时间
     */
    public static Date getDateWithTimeZero(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.setLenient(false);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取日期所在月份第一天0点的时间
     * @param date 日期
     * @return 该日期当月0点的时间
     */
    public static Date getMonthFirstDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.setLenient(false);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取指定日期第二天0点的时间
     * @param date 日期
     * @return 该日期第二天0点的时间
     */
    public static Date getNextDayWithTimeZero(Date date) {
        return getDateWithTimeZero(DateUtils.addDays(date, 1));
    }

    /**
     * 复制源日期中的时间部分到目标日期中
     * @param srcDate 要复制的源日期
     * @param dstDate 复制的目标日期
     * @return 将目标日期中的时间部分替换为源日期时间部分后的新日期
     */
    public static Date copyTime(Date srcDate, Date dstDate) {
        Calendar dst = Calendar.getInstance();
        dst.setTime(dstDate);
        dst.setLenient(false);
        {
            Calendar src = Calendar.getInstance();
            src.setTime(srcDate);
            dst.set(Calendar.HOUR_OF_DAY, src.get(Calendar.HOUR_OF_DAY));
            dst.set(Calendar.MINUTE, src.get(Calendar.MINUTE));
            dst.set(Calendar.SECOND, src.get(Calendar.SECOND));
            dst.set(Calendar.MILLISECOND, src.get(Calendar.MILLISECOND));
        }
        return dst.getTime();
    }

    /*----------------------------------------
     *                数值计算算接口
     *-----------------------------------------*/
    /**
     * 设置精度（四舍五入）
     * @param decimal 数值
     * @param scale 精度
     * @return 新值
     */
    public static BigDecimal scale(BigDecimal decimal, int scale) {
        return decimal.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 设置精度=2
     * @param decimal 数值
     * @return 设置精度后的新值
     */
    public static BigDecimal scale2(BigDecimal decimal) {
        return scale(decimal, 2);
    }

    /**
     * 除法:d1/d2,保留2位小数,四舍五入  - 除法需要设置精度,否则若出现无限循环的小数会抛异常
     * @param decimal 被除数
     * @param divisor 除数
     * @return 除后的值
     */
    public static BigDecimal divide(BigDecimal decimal, BigDecimal divisor) {
        return decimal.divide(divisor, 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     *  乘法:d1*d2，四舍五入保留两位小数
     *  @param d1 数值1
     *  @param d2 数值2
     *  @return 结果
     */
    public static BigDecimal multiply(BigDecimal d1, BigDecimal d2) {
        return scale2(d1.multiply(d2));
    }

    /**
     * 分转换为元
     * @param cent 分
     * @return 元
     */
    public static BigDecimal cent2Yuan(long cent) {
        return divide(new BigDecimal(cent), DEC100);
    }

    /**
     * 分转换为元并移除小数位末尾的无效0
     * @param cent 分
     * @return 元
     */
    public static String cent2YuanTrimZero(long cent) {
        return CENT_ZERO_TRIMMER.format(cent2Yuan(cent));
    }

    /**
     * 元转换为分
     * @param yuan
     * @return
     */
    public static int yuan2Cent(BigDecimal yuan) {
        return yuan.multiply(DEC100).intValue();
    }

    /*----------------------------------------
     *                io接口
     *-----------------------------------------*/
    /**
     * 关闭资源
     * @param c 资源
     */
    public static void close(AutoCloseable c) {
        if (c != null) {
            try {
                c.close();
            } catch (Exception ioe) {
                // ignore
            }
        }
    }

    /**
     * 关闭资源列表
     * @param arr 资源列表
     */
    public static void close(AutoCloseable... arr) {
        if (arr != null) {
            for (AutoCloseable c : arr) {
                close(c);
            }
        }
    }

    /**
     * 关闭XmlWriter
     * @param writer  xmlWriter
     */
    public static void close(XMLWriter writer) {
        if (null != writer) {
            try {
                writer.close();
            } catch (IOException e) {
                // ignore
            }
        }
    }

    /**
     * 关闭LineIterator
     */
    public static void close(LineIterator itr) {
        LineIterator.closeQuietly(itr);
    }

    /**
     * 抛出异常回退事务
     * @param msg 消息
     */
    public static void roll(String msg) {
        throw new RuntimeException(msg);
    }

    /**
     * 获取文件名中的主文件名（除扩展名以外的部分）
     * @param fileName 文件名
     * @return 扩展名，如果没有，返回空字符串
     */
    public static String getMainFileName(String fileName) {
        return fileName.contains(".") ? fileName.substring(0, fileName.lastIndexOf(".")) : fileName;
    }

    /**
     * 获取文件名中的扩展名
     * @param fileName 文件名
     * @return 扩展名，如果没有，返回空字符串
     */
    public static String getExtFileName(String fileName) {
        int idx = fileName.lastIndexOf(".");
        return idx == -1 ? "" : fileName.substring(idx + 1);
    }

    /**
     * 获取classpath下的资源
     * @param path 路径
     * @return URL, 如不存在返回null
     */
    public static URL getResource(String path) {
        return BaseUtil.class.getClassLoader().getResource(path);
    }

    /**
     * 获取classpath中的文件InputStream
     * @param path 路径
     * @return InputStream, 如不存在返回null
     */
    public static InputStream getResourceAsStream(String path) {
        return BaseUtil.class.getClassLoader().getResourceAsStream(path);
    }

    /**
     * 获取classpath下指定文本文件的内容
     * @param path 路径
     * @param encoding 文件编码
     * @return 文件的文本内容
     * @throws IOException 文件不存在或访问异常
     */
    public static String getResourceFileContent(String path, String encoding) throws IOException {
        InputStream ins = getResourceAsStream(path);
        if (null == ins) {
            throw new FileNotFoundException("文件不存在: " + path);
        }

        return IOUtils.toString(ins, encoding);
    }


    /**
     * 从url中提取主机路径(http://host:port部分)
     * @param url 地址
     * @return http://host:port
     */
    public static String getHostPath(String url) {
        try {
            URL u = new URL(url);
            return u.getProtocol() + "://" + u.getHost() + (u.getPort() > 0 ? ":" + u.getPort() : "");
        } catch (MalformedURLException e) {
            log.error("url解析失败: " + url, e);
            return "";
        }
    }

    /**
     * 将参数拼接成文件路径<br/>
     * 如: /aa,11,file.txt => /aa/11/file.txt
     * @param arr 资源列表
     * @return 路径
     */
    public static String buildPath(Object... arr) {
        StringBuilder sb = new StringBuilder();
        for (Object o : arr) {
            sb.append(String.valueOf(o)).append(File.separator);
        }

        // 移除末尾的分隔符
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }


    /**
     * 打印异常栈信息
     * @param t t
     * @return 异常栈
     */
    public static String printStackTrace(Throwable t) {
        try (StringWriter sw = new StringWriter();
             PrintWriter pw = new PrintWriter(sw, true)){
            t.printStackTrace(pw);
            return sw.getBuffer().toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 过滤掉字符串中指定的字符
     * @param str 原字符串数据
     * @param filterStr 要过滤的内容
     * @return 新字符串数据
     */
    public static String filterStr(String str, String... filterStr){
        for (String s : filterStr) {
            str = str.replace(s, "");
        }
        return str;
    }
}
