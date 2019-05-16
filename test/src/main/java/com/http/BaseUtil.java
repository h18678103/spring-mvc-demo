package com.http;

import com.alibaba.fastjson.JSON;
import java.io.Closeable;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 基础工具
 * @author devin.xu
 * @date 2016.06
 */
public class BaseUtil {

    /**
     * 获取classpath中的文件InputStream
     * @param path 路径
     * @return InputStream, 如不存在返回null
     */
    public static InputStream getResourceAsStream(String path) {
        return BaseUtil.class.getClassLoader().getResourceAsStream(path);
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
     * 生成签名
     */
    public static String getSign(Map<String, ?> map, String key, String... ex) {
        // 将map转换为key&value形式的连接字符串(去除空值和指定参数)
        String queryStr = getSortedQueryStrWithoutUrlEncode(map, ex);

        // md5加密,转大写
        return CodecUtil.md5(queryStr + key, BaseConsts.UTF8).toUpperCase();
    }


    /**
     * 对象转json
     *
     * @param obj 要转换的对象
     * @return
     */
    public static String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * json转对象
     *
     * @param json  json文本
     * @param clazz 解析的类
     * @return
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }


    /**
     * 将map中的键值对按字典排序并转换为key&value形式的连接字符串，
     * 会略过excludeKeys中包含的key，以及value为空的值。
     *
     * @param map         要转换的map
     * @param excludeKeys 要排除的键的集合
     * @param urlEncode   是否对值进行urlEncode
     * @return 排序后的key&value字符串
     */
    public static String getSortedQueryStrEx(Map<String, ?> map, Collection<String> excludeKeys, boolean urlEncode) {
        if (isEmpty(map)) {
            return "";
        }

        // keys排序
        List<String> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);

        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            // 略过例外
            if (!isEmpty(excludeKeys) && excludeKeys.contains(key)) {
                continue;
            }

            // 略过空值
            String value = String.valueOf(map.get(key));
            if (StringUtils.isBlank(value)) {
                continue;
            }

            sb.append(key).append("=").append(urlEncode ? urlEncode(value) : value).append("&");
        }

        // remove '&'
        sb.setLength(sb.length() - 1);

        return sb.toString();
    }


    /**
     * url encode/decode
     */
    public static String urlEncode(String s, String charset) {
        if (isBlank(s)) {
            return s;
        }

        try {
            return URLEncoder.encode(s, charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String urlEncode(String s) {
        return urlEncode(s, BaseConsts.UTF8);
    }

    public static String urlDecode(String s, String charset) {
        if (isBlank(s)) {
            return s;
        }

        try {
            return URLDecoder.decode(s, charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String urlDecode(String s) {
        return urlDecode(s, BaseConsts.UTF8);
    }



    /**
     * 将map中的元素按k1=v1&k2=v2形式拼接成连接字符串
     *
     * @param map         要处理的map（建议使用TreeMap提高效率）
     * @param urlEncode   是否对值进行urlEncode
     * @param ignoreBlank 是否忽略空值
     * @param excludeKeys 排除的key
     * @return 按key排序后的querystring
     */
    @SuppressWarnings("unchecked")
    public static String getSortedQueryStrEx(Map<String, ?> map,
                                             boolean urlEncode, boolean ignoreBlank, String... excludeKeys) {
        if (isEmpty(map)) {
            return "";
        }

        // 使用treeMap排序
        TreeMap<String, Object> sortMap = null;
        if (map instanceof TreeMap) {
            sortMap = (TreeMap<String, Object>) map;
        } else {
            // 移除null，放入TreeMap
            map.remove(null);
            sortMap = new TreeMap<>(map);
        }

        return mapToQueryString(sortMap, urlEncode, ignoreBlank, excludeKeys);
    }


    /**
     * map直接转换为querystring,不改变map中的元素顺序
     *
     * @param map         map
     * @param urlEncode   是否urlEncode
     * @param ignoreBlank 是否忽略空值
     * @param excludeKeys 要排除的字段
     * @return querystring
     */
    public static String mapToQueryString(Map<String, ?> map,
                                          boolean urlEncode, boolean ignoreBlank, String... excludeKeys) {
        if (BaseUtil.isEmpty(map)) {
            return "";
        }

        // 遍历拼接query string
        StringBuilder sb = new StringBuilder();
        String value = "";
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            // 要排除的key
            if (!isEmpty(excludeKeys) && ArrayUtils.contains(excludeKeys, entry.getKey())) {
                continue;
            }

            // 忽略空值(object->string)
            value = (entry.getValue() == null ? "" : String.valueOf(entry.getValue()));
            if (ignoreBlank && isBlank(value)) {
                continue;
            }

            // k=v (urlEncode)
            sb.append(entry.getKey()).append("=").append(urlEncode ? urlEncode(value) : value).append("&");
        }

        // 移除末尾的'&'
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }


    public static String getSortedQueryStrWithUrlEncode(Map<String, ?> map, String... ex) {
        return getSortedQueryStrEx(map, 0 == ex.length ? null : Arrays.asList(ex), true);
    }

    public static String getSortedQueryStrWithoutUrlEncode(Map<String, ?> map, boolean ignoreBlank, String... excludeKeys) {
        return getSortedQueryStrEx(map, false, ignoreBlank, excludeKeys);
    }

    public static String getSortedQueryStrWithoutUrlEncode(Map<String, ?> map, String... ex) {
        return getSortedQueryStrEx(map, 0 == ex.length ? null : Arrays.asList(ex), false);
    }


    /**
     * 关闭资源列表
     *
     * @param arr 资源列表
     */
    public static void close(AutoCloseable... arr) {
        if (arr != null) {
            for (AutoCloseable c : arr) {
                close(c);
            }
        }
    }

    public static void close(Closeable... arr) {
        IOUtils.closeQuietly(arr);
    }


    // 拼接成字符串
    public static String join(Object[] arr, String separator) {
        return StringUtils.join(arr, separator);
    }

    // 默认按','拼接
    public static String join(Object[] arr) {
        return join(arr, ",");
    }

    public static boolean isBlank(String s) {
        return StringUtils.isBlank(s);
    }

    public static boolean notBlank(String s) {
        return !isBlank(s);
    }

    public static <T> boolean isEmpty(byte[] arr) {
        return null == arr || 0 == arr.length;
    }

    public static <T> boolean notEmpty(byte[] arr) {
        return !isEmpty(arr);
    }

    /**
     * 集合/Map方法
     **/
    public static boolean isEmpty(Collection<?> c) {
        return null == c || 0 == c.size();
    }

    public static boolean notEmpty(Collection<?> c) {
        return !isEmpty(c);
    }

    public static boolean isEmpty(Map<?, ?> m) {
        return null == m || 0 == m.size();
    }

    public static <T> boolean isEmpty(T[] arr) {
        return null == arr || 0 == arr.length;
    }

    public static <T> boolean notEmpty(T[] arr) {
        return !isEmpty(arr);
    }
}
