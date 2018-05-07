package cipher;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/**
 * @author huqinsong
 * @date 2018/5/4
 */
public class DigestTetst {

    /**
     * 生成摘要
     * @param data 源数据
     * @param algorithm 摘要算法：MD5|SHA1|SHA256|SHA512...
     * @return 摘要数据
     */
    public static byte[] digest(byte[] data, String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm).digest(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成摘要，返回base64Encode后的字符串
     * @param data 源数据
     * @param algorithm 摘要算法：MD5|SHA1|SHA256|SHA512...
     * @return 摘要数据base64Encode后的字符串
     */
    public static String digestStr(byte[] data, String algorithm) {
        return Base64.encodeBase64String(digest(data, algorithm));
    }



    /**
     * 生成md5摘要
     * @param txt 文本
     * @param encoding 编码
     * @return md5摘要
     */
    public static String md5Digest(String txt, String encoding) {
        try {
            return digestStr(txt.getBytes(encoding), MessageDigestAlgorithms.MD5);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成SHA-1摘要
     * @param txt 文本
     * @param encoding 编码
     * @return sha1摘要
     */
    public static String sha1Digest(String txt, String encoding) {
        try {
            return digestStr(txt.getBytes(encoding), MessageDigestAlgorithms.SHA_1);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String s = "abdfwae2132142343423423";
        String s1 = DigestTetst.md5Digest(s, "UTF-8");
        System.out.println(s1);
        System.out.println(s1.length());
        String s2 = DigestTetst.sha1Digest(s, "UTF-8");
        System.out.println(s2);
        System.out.println(s2.length());
    }
}
