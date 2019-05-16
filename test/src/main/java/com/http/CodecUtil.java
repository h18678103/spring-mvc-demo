package com.http;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import static com.http.BaseUtil.notBlank;
import static java.util.regex.Pattern.*;

/**
 * 编码工具(加解密、编码转换)
 * @author devin.xu
 * @date 2016.08
 */
public final class CodecUtil {
    
    /** 默认的字符编码：UTF8 */
    private static final String DFLT_ENCODING = BaseConsts.UTF8;
    
    /** unicode格式匹配 */
    private static final Pattern UNICODE_PATTERN = compile("(\\\\u(\\p{XDigit}{4}))");
    private static final Pattern HEX_PATTERN = compile("((\\\\0*x\\p{XDigit}{2})+)");
    
    /** 加密算法 */
    private static final String AES = "AES";
    private static final String DES = "DES";
    private static final String RSA = "RSA";
    public static final String RSA_ECB_PKCS1PADDING = "RSA/ECB/PKCS1Padding";
    
    /** RSA数字签名/签名验证算法*/
    private static final String SHA1_WITH_RSA = "SHA1WithRSA";
    
    /** AES签名算法 */
    private static final String AES_CEB_PKCS5PADDING = "AES/ECB/PKCS5Padding";
    
    /** 3DES-DESede摘要算法 */
    private static final String DES_EDE_CEB_PKCS5PADDING = "DESede/ECB/PKCS5Padding";
    /** 3DES-DESede密钥类型 */
    private static final String DES_EDE_KEY_ALGORITHM = "DESede";

    /** HMAC签名算法：接受任何大小的密钥，并产生长度为 160 位的哈希序列 */
    private static final String HMAC_SHA1 = "HmacSHA1";

    /**
     * RSA分段加密的块大小：
     * 1024位的证书，加密时最大支持117个字节，解密时为128；
     * 2048位的证书，加密时最大支持245个字节，解密时为256(重要)。
     * 加密时支持的最大字节数：证书位数/8 -11（比如：2048位的证书，支持的最大加密字节数：2048/8 - 11 = 245）
     */
    private static final int RSA_ENCRYPT_BLOCK_LEN  = 117;
    private static final int RSA_DECRYPT_BLOCK_LEN  = 128;
    private static final int RSA_ENCRYPT_BLOCK_LEN_2048 = 245;
    private static final int RSA_DECRYPT_BLOCK_LEN_2048 = 256;

    /**
     * 生成摘要
     * @param data 源数据
     * @param algorithm 摘要算法：MD5|SHA1|SHA256|SHA512...
     * @return 摘要数据
     */
    public static byte[] digest(byte[] data, String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm).digest(data);
        } catch (NoSuchAlgorithmException e) {
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
        return base64EncodeString(digest(data, algorithm));
    }
    
    /**
     * 计算md5值
     * @param txt 文本
     * @param encoding 编码
     * @return md5值
     */
    public static String md5(String txt, String encoding) {
		try {
			return notBlank(txt) ? DigestUtils.md5Hex(txt.getBytes(encoding)) : txt;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
    /**
     * 计算md5值，使用默认的utf8编码
     * @param txt 文本
     * @return md5值
     */
	public static String md5(String txt) {
		return md5(txt, DFLT_ENCODING);
	}
	
	/**
	 * 计算文件的md5摘要
	 * @param file 文件
	 * @return md5值
	 * @throws IOException 文件读取异常
	 */
	public static byte[] md5Digest(File file) throws IOException {
	    try (InputStream ins = new FileInputStream(file);) {
	        MessageDigest md5 = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
    	    byte[] buffer = new byte[8 * BaseConsts.KB];
    	    int len;
    	    
    	    while((len = ins.read(buffer)) != -1){
    	        md5.update(buffer, 0, len);
    	    }
    	    
    	    return md5.digest();
	    } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
	    }
	}
    
    /**
     * 计算文件的md5值
     * @param file 文件
     * @return md5值
     * @throws IOException 文件读取异常
     */
    public static String md5(File file) throws IOException {
        return Hex.encodeHexString(md5Digest(file));
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

    /**
     * 用HmacSHA1算法生成签名
     * @param txt 要签名的文本
     * @param key 签名的密钥
     * @param encoding 编码
     * @return 签名
     */
    public static byte[] hmacSha1(String txt, String key, String encoding) {
        try {
            Mac mac = Mac.getInstance(HMAC_SHA1);
            mac.init(new SecretKeySpec(key.getBytes(encoding), HMAC_SHA1));
            return mac.doFinal(txt.getBytes(encoding));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 用HmacSHA1算法+Base64Encode生成签名
     * @param txt 要签名的文本
     * @param key 签名的密钥
     * @param encoding 编码
     * @return 签名
     */
    public static String hmacSha1Sign(String txt, String key, String encoding) {
        return base64EncodeString(hmacSha1(txt, key, encoding));
    }
    
    /**
     * 对字节数组进行十六进制编码
     * @param data 要编码的数据
     * @return Base64编码后的数据
     */
    public static char[] hexEncode(byte[] data) {
        return Hex.encodeHex(data);
    }
    
    /**
     * 对字符串进行十六进制编码，使用默认的utf8编码
     * @param txt 文本
     * @return Base64编码后的数据
     */
    public static char[] hexEncode(String txt) {
        try {
            return notBlank(txt) ? hexEncode(txt.getBytes(DFLT_ENCODING)) : null;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 十六进制编码，生成编码字符串
     * @param data 要编码的数据
     * @return 十六进制字符串
     */
    public static String hexEncodeString(byte[] data) {
        return Hex.encodeHexString(data);
    }

    /**
     * 转十六进制编码
     * @param txt 文本
     * @param encoding 编码格式
     * @return 十六进制编码字符串
     */
    public static String hexEncodeString(String txt, String encoding) {
        try {
            return notBlank(txt) ? hexEncodeString(txt.getBytes(encoding)) : txt;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * base64编码，生成编码字符串（使用默认的utf8编码）
     * @param txt 要编码的字符串
     * @return Base64编码后的字符串
     */
    public static String hexEncodeString(String txt) {
        try {
            return notBlank(txt) ? hexEncodeString(txt.getBytes(DFLT_ENCODING)) : txt;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 十六进制字符解码
     * @param data 十六进制字符
     * @return 解码后的数据
     */
    public static byte[] hexDecode(char[] data) {
        try {
            return Hex.decodeHex(data);
        } catch (DecoderException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 十六进制字符串解码
     * @param s 十六进制字符串
     * @return 解码后的数据
     */
    public static byte[] hexDecode(String s) {
        return hexDecode(s.toCharArray());
    }
    
    /**
     * 十六进制字符串解码
     * @param s 十六进制字符串
     * @param charset 明文串使用的字符集
     * @return 解码后的字符串
     */
    public static String hexDecodeString(String s, String charset) {
        try {
            return new String(hexDecode(s), charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
	 * base64编码，生成编码字符串
	 * @param data 要编码的数据
	 * @return Base64字符串
	 */
	public static String base64EncodeString(byte[] data) {
	    return Base64.encodeBase64String(data);
	}
	
	/**
	 * base64编码，生成编码字符串（使用默认的utf8编码）
	 * @param txt 要编码的字符串
	 * @return Base64编码后的字符串
	 */
	public static String base64EncodeString(String txt) {
	    try {
            return notBlank(txt) ? base64EncodeString(txt.getBytes(DFLT_ENCODING)) : txt;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
	
	/**
	 * 对字节数组进行base64编码
	 * @param data 要编码的数据
	 * @return Base64编码后的数据
	 */
	public static byte[] base64Encode(byte[] data) {
	    return Base64.encodeBase64(data);
	}
	
	/**
	 * 对字符串进行base64编码，使用默认的utf8编码
	 * @param txt 文本
	 * @return Base64编码后的数据
	 */
	public static byte[] base64Encode(String txt) {
	    try {
            return notBlank(txt) ? base64Encode(txt.getBytes(DFLT_ENCODING)) : null;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
	}
	
	/**
	 * 对字符串进行base64解码
	 * @param txt 文本
	 * @return Base64解码后的数据
	 */
	public static byte[] base64Decode(String txt) {
	    try {
            return notBlank(txt) ? base64Decode(txt.getBytes(DFLT_ENCODING)) : null;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
	}

    /**
     * base64解码
     * @param data
     * @return
     */
    public static byte[] base64Decode(byte[] data) {
        return Base64.decodeBase64(data);
    }

	/**
	 * base64解码，生成字符串（使用默认的utf8编码）
	 * @param data 数据
	 * @return 解码后的字符串
	 */
	public static String base64DecodeString(byte[] data) {
        try {
            return new String(base64Decode(data), DFLT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    
	/**
	 * 对字符串进行base64解码
	 * @param txt 要编码的文本
	 * @return 编码后的文本
	 */
    public static String base64DecodeString(String txt) {
        try {
            return notBlank(txt) ? new String(base64Decode(txt), DFLT_ENCODING) : txt;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
	
	/**
	 * 生成AES密钥
	 * @param keySize 密钥长度：128|192|256
	 * @return AES密钥
	 */
    public static byte[] aesGetKey(int keySize) {
        KeyGenerator kg;
        try {
            kg = KeyGenerator.getInstance(AES);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        
        if (keySize != 128 || keySize != 192 || keySize != 256) {
            throw new IllegalArgumentException("密钥长度应为：128|192|256其中之一");
        }

        kg.init(keySize);
        return kg.generateKey().getEncoded();
    }
    
    /**
     * 生成AES密钥，默认128位
     * @return AES密钥
     */
    public static byte[] aesGetKey() {
        return aesGetKey(128);
    }
    
    /**
     * AES加密
     * @param algorithm 加密算法
     * @param data 要加密的数据
     * @param key AES密钥
     * @return 加密后的数据
     */
    public static byte[] aesEncrypt(String algorithm, byte[] data, byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, AES));
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * AES加密（返回Base64Encode之后的字符串）
     * @param algorithm 加密算法
     * @param data 要加密的数据
     * @param key AES密钥
     * @return AES+Base64加密后的字符串
     */
	public static String aesEncryptStr(String algorithm, byte[] data, byte[] key) {
        byte[] barr = aesEncrypt(algorithm, data, key);
        return base64EncodeString(barr);
    }

	/**
	 * AES加密（使用默认的AES/ECB/PKCS5Padding算法）
	 * @param data 要加密的数据
	 * @param key AES密钥
	 * @return 加密后的数据
	 */
	public static byte[] aesEncrypt(byte[] data, byte[] key) {
	    return aesEncrypt(AES_CEB_PKCS5PADDING, data, key);
	}
	
	/**
     * AES加密（返回Base64Encode之后的字符串）
     * @param data 要加密的数据
     * @param key AES密钥
     * @return 加密后的字符串
     */
    public static String aesEncryptStr(byte[] data, byte[] key) {
        return aesEncryptStr(AES_CEB_PKCS5PADDING, data, key);
    }

    /**
     * AES加密（返回HexEncode之后的字符串）
     * @param algorithm 加密算法
     * @param data 要加密的数据
     * @param key AES密钥
     * @return AES+Base64加密后的字符串
     */
    public static String aesHexEncryptStr(String algorithm, byte[] data, byte[] key) {
        byte[] barr = aesEncrypt(algorithm, data, key);
        return hexEncodeString(barr);
    }

    /**
     * AES加密（返回HexEncode之后的字符串）
     * @param data 要加密的数据
     * @param key AES密钥
     * @return 加密后的字符串
     */
    public static String aesHexEncryptStr(byte[] data, byte[] key) {
        return aesHexEncryptStr(AES_CEB_PKCS5PADDING, data, key);
    }

	/**
	 * AES解密
	 * @param data 要解密的数据
	 * @param key AES密钥
	 * @return 解密后的数据
	 */
    public static byte[] aesDecryptData(String algorithm, byte[] data, byte[] key) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, AES);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * AES解密生成字符串
     * @param algorithm 加密算法
     * @param data 密文内容
     * @param key 密钥
     * @return 解密后的字符串
     */
    public static String aesDecrypt(String algorithm, byte[] data, byte[] key) {
        byte[] barr = aesDecryptData(algorithm, data, key);
        return new String(barr);
    }
    
    /**
     * AES解密（使用默认的AES/ECB/PKCS5Padding算法）
     * @param data 要解密的数据
     * @param key AES密钥
     * @return 解密后的数据
     */
    public static byte[] aesDecryptData(byte[] data, byte[] key) {
        return aesDecryptData(AES_CEB_PKCS5PADDING, data, key);
    }
    
    /**
     * AES解密生成字符串
     * @param data 密文内容
     * @param key 密钥
     * @return 解密后的字符串
     */
    public static String aesDecrypt(byte[] data, byte[] key) {
        return aesDecrypt(AES_CEB_PKCS5PADDING, data, key);
    }
    
    /**
     * DES加密
     * @param txt 要加密的文本
     * @param key 密钥（对称）
     * @param encoding 编码
     * @return 加密后的密文
     */
    public static String desEncrypt(String txt, String key, String encoding) {
        try {
            // 密钥长度=8
            byte[] kdata = new byte[8];
            System.arraycopy(key.getBytes(encoding), 0, kdata, 0, kdata.length);
            Cipher cipher = Cipher.getInstance(DES);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kdata, DES));
            return Hex.encodeHexString(cipher.doFinal(txt.getBytes(encoding)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 3DES加密-DESede算法
     * @param txt 要加密的文本
     * @param key 密钥（对称）
     * @param encoding 编码
     * @return 加密后的密文
     */
    public static byte[] desEdeEncryptData(String txt, String key, String encoding) {
        try {
            // 密钥长度=24
            byte[] kdata = new byte[24];
            System.arraycopy(key.getBytes(encoding), 0, kdata, 0, kdata.length);
            Cipher cipher = Cipher.getInstance(DES_EDE_CEB_PKCS5PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, SecretKeyFactory.getInstance(DES_EDE_KEY_ALGORITHM).generateSecret(new DESedeKeySpec(kdata)));
            return cipher.doFinal(txt.getBytes(encoding));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 3DES加密-DESede算法
     * @param txt 要加密的文本
     * @param key 密钥（对称）
     * @param encoding 编码
     * @return 加密后的密文
     */
    public static String desEdeEncrypt(String txt, String key, String encoding) {
        byte[] data = desEdeEncryptData(txt, key, encoding);
        return hexEncodeString(data);
    }

    /**
     * 3DES加密-DESede算法
     * @param txt 要加密的文本
     * @param key 密钥（对称）
     * @param encoding 编码
     * @return 加密后的密文
     */
    public static String desEdeEncryptWithBase64(String txt, String key, String encoding) {
        byte[] data = desEdeEncryptData(txt, key, encoding);
        return base64EncodeString(data);
    }

    /**
     * 3DES解密-DESede算法
     * @param data 密文data
     * @param key 密钥
     * @param encoding 密文编码
     * @return 解密后的明文
     */
    public static String desEdeDecrypt(byte[] data, String key, String encoding) {
        try {
            // 密钥长度=24
            byte[] kdata = new byte[24];
            System.arraycopy(key.getBytes(encoding), 0, kdata, 0, kdata.length);
            Cipher cipher = Cipher.getInstance(DES_EDE_CEB_PKCS5PADDING);
            cipher.init(Cipher.DECRYPT_MODE, SecretKeyFactory.getInstance(DES_EDE_KEY_ALGORITHM).generateSecret(new DESedeKeySpec(kdata)));
            return new String(cipher.doFinal(data), encoding);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 3DES解密-DESede算法
     * @param txt 密文文本
     * @param key 密钥
     * @param encoding 密文编码
     * @return 解密后的明文
     */
    public static String desEdeDecrypt(String txt, String key, String encoding) {
        try {
            return desEdeDecrypt(Hex.decodeHex(txt.toCharArray()), key, encoding);
        } catch (DecoderException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * DES加密
     * @param cipherTxt 密文
     * @param key 密钥（对称）
     * @param encoding 编码
     * @return 解密后的明文
     */
    public static String desDecrypt(String cipherTxt, String key, String encoding) {
        if (BaseUtil.isBlank(cipherTxt)) {
            return "";
        }
        
        try {
            byte[] kdata = new byte[8];
            System.arraycopy(key.getBytes(encoding), 0, kdata, 0, kdata.length);
            Cipher cipher = Cipher.getInstance(DES);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kdata, DES));
            return new String(cipher.doFinal(Hex.decodeHex(cipherTxt.toCharArray())));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 从InputStream中读取密钥文本
     * @param ins 密钥文件的InputStream
     * @return 密文文本（单行）
     * @throws IOException IO读取异常
     */
    public static String loadRsaKey(InputStream ins) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(ins));) {
            StringBuilder sb = new StringBuilder();
            String line = null;
            while (notBlank(line = br.readLine())) {
                if (line.charAt(0) != '-') {
                    sb.append(line);
                }
            }
            
            return sb.toString();
        }
    }
    
    /**
     * 从rsa密钥文件中读取密钥文本
     * @param keyFile 密钥文件
     * @return
     * @throws IOException 
     */
    public static String loadRsaKeyFromFile(File keyFile) throws IOException {
        return loadRsaKey(new FileInputStream(keyFile));
    }
    
    /**
     * 从rsa密钥文件中读取密钥文本
     * @param filepath 密钥文件路径
     * @return 密钥文本
     * @throws IOException 加载异常
     */
    public static String loadRsaKeyFromFile(String filepath) throws IOException {
        return loadRsaKeyFromFile(new File(filepath));
    }
    
    /**
     * 加载X509格式的RSA公钥（.cer格式文件）
     * @param filepath 公钥文件路径
     * @return 公钥
     * @throws Exception 加载出错
     */
    public static PublicKey loadPublicKeyFromX509File(String filepath) throws Exception {
        try (InputStream inputStream = new FileInputStream(new File(filepath))) {
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) certFactory.generateCertificate(inputStream);
            return cert.getPublicKey();
        }
    }
    
    /**
     * X509格式(.cer)转换为PEM
     * @param filepath 公钥文件路径
     * @return PEM格式的密钥文本
     * @throws Exception 加载出错
     */
    public static String transferPublicKeyX509ToPem(String filepath) throws Exception {
        PublicKey key = loadPublicKeyFromX509File(filepath);
        return base64EncodeString(key.getEncoded());
    }
    
    /**
     * X509格式(.cer)转换为PEM并格式化
     * @param filepath 公钥文件路径
     * @return PEM格式的密钥文本
     * @throws Exception 加载出错
     */
    public static String transferPublicKeyX509ToPemFormat(String filepath) throws Exception {
        // 生成pem文本
        String txt = transferPublicKeyX509ToPem(filepath);
        
        // 按64位分段
        final int segSize = 64;
        ContentBuilder cb = new ContentBuilder().add("-----BEGIN PUBLIC KEY-----");
        while (txt.length() > segSize) {
            cb.cr().add(txt.substring(0, segSize));
            txt = txt.substring(segSize);
        }
        
        return cb.cr().add(txt).cr().add("-----END PUBLIC KEY-----").toString();
    }
    
    /**
     * 从PFX证书中加载私钥
     * @param filepath 私钥文件路径
     * @param password 证书密码
     * @return 证书
     */
    public static PrivateKey loadPrivateKeyFromPKCS12File(String filepath, String password) throws Exception {
        KeyStore ks = KeyStore.getInstance("PKCS12");
        char[] passwdChars = BaseUtil.isBlank(password) ? null : password.toCharArray();
        try (InputStream inputStream = new FileInputStream(new File(filepath))) {
            ks.load(inputStream, passwdChars);
            Enumeration enumas = ks.aliases();
            if (!enumas.hasMoreElements()) {
                throw new RuntimeException("读取证书失败");
            }
    
            return (PrivateKey) ks.getKey((String) enumas.nextElement(), passwdChars);
        }
    }
    
    /**
     * PFX格式(.pfx)转换为PEM
     * @param filepath 私钥文件路径
     * @return PEM格式的密钥文本
     * @throws Exception 加载出错
     */
    public static String transferPrivateKeyPKCS12ToPem(String filepath, String password) throws Exception {
        PrivateKey key = loadPrivateKeyFromPKCS12File(filepath, password);
        return base64EncodeString(key.getEncoded());
    }
    
    /**
     * PFX格式(.pfx)转换为PEM并格式化
     * @param filepath 私钥文件路径
     * @return PEM格式的密钥文本
     * @throws Exception 加载出错
     */
    public static String transferPrivateKeyPKCS12ToPemFormat(String filepath, String password) throws Exception {
        // 生成pem文本
        String txt = transferPrivateKeyPKCS12ToPem(filepath, password);
        
        // 按64位分段
        final int segSize = 64;
        ContentBuilder cb = new ContentBuilder().add("-----BEGIN PRIVATE KEY-----");
        while (txt.length() > segSize) {
            cb.cr().add(txt.substring(0, segSize));
            txt = txt.substring(segSize);
        }
        
        return cb.cr().add(txt).cr().add("-----END PRIVATE KEY-----").toString();
    }
    
    /**
     * 分段计算摘要
     * @param cipher 算法对象
     * @param data 数据
     * @param segLen 分段长度
     * @return 加密后的数据
     * @throws Exception exp
     */
    public static byte[] segmentCipher(Cipher cipher, final byte[] data, final int segLen) throws Exception {
        byte[] cache;
        int offSet = 0;
        
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            while (data.length > offSet) {
                cache = data.length > offSet + segLen
                        ? cipher.doFinal(data, offSet, segLen)
                        : cipher.doFinal(data, offSet, data.length - offSet);
                out.write(cache, 0, cache.length);
                offSet += segLen;
            }
            
            return out.toByteArray();
        }
    }
    
    /**
     * RSA分段加密
     * @param txt 要加密的明文
     * @param publicKey rsa公钥
     * @param encoding 明文编码格式
     * @param blockLen 分段加密的块大小
     * @return base64编码后的密文数据
     * @throws Exception 加密异常
     */
    public static byte[] rsaEncryptData(String algorithm, String txt, String publicKey, String encoding, int blockLen)
            throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, KeyFactory.getInstance(RSA).generatePublic(new X509EncodedKeySpec(base64Decode(publicKey))));
        return segmentCipher(cipher, txt.getBytes(encoding), blockLen);
    }
    
    /**
     * RSA分段加密
     * @param txt 要加密的明文
     * @param publicKey rsa公钥
     * @param encoding 明文编码格式
     * @return base64编码后的密文数据
     * @throws Exception 加密异常
     */
    public static byte[] rsaEncryptData(String algorithm, String txt, String publicKey, String encoding)
            throws Exception {
        return rsaEncryptData(algorithm, txt, publicKey, encoding, RSA_ENCRYPT_BLOCK_LEN);
    }
    
    /**
     * RSA分段加密
     * @param txt 要加密的明文
     * @param publicKey rsa公钥
     * @param encoding 明文编码格式
     * @return base64编码后的密文数据
     * @throws Exception 加密异常
     */
    public static byte[] rsaEncryptData(String txt, String publicKey, String encoding)
            throws Exception {
        return rsaEncryptData(RSA, txt, publicKey, encoding);
    }
    
    /**
     * RSA分段加密，并对密文做Base64编码
     * @param txt 要加密的明文
     * @param publicKey rsa公钥
     * @param encoding 明文编码格式
     * @param blockLen 分段加密的块大小
     * @return base64编码后的密文字符串
     * @throws Exception 加密异常
     */
    public static String rsaEncrypt(String algorithm, String txt, String publicKey, String encoding, int blockLen)
            throws Exception {
        byte[] data = rsaEncryptData(algorithm, txt, publicKey, encoding, blockLen);
        return base64EncodeString(data);
    }
    
    /**
     * RSA公钥加密（1024位证书）
     * @param txt 要加密的明文
     * @param publicKey rsa公钥
     * @param encoding 明文编码格式
     * @return
     * @throws Exception
     */
    public static String rsaEncrypt(String algorithm, String txt, String publicKey, String encoding) throws Exception {
        return rsaEncrypt(algorithm, txt, publicKey, encoding, RSA_ENCRYPT_BLOCK_LEN);
    }

    /**
     * RSA公钥加密（2048位证书）
     * @param txt 要加密的明文
     * @param publicKey rsa公钥
     * @param encoding 明文编码格式
     * @return
     * @throws Exception
     */
    public static String rsaEncrypt2048(String algorithm, String txt, String publicKey, String encoding) throws Exception {
        return rsaEncrypt(algorithm, txt, publicKey, encoding, RSA_ENCRYPT_BLOCK_LEN_2048);
    }
    
    /**
     * RSA公钥加密（1024位证书）
     * @param txt 要加密的明文
     * @param publicKey rsa公钥
     * @param encoding 明文编码格式
     * @return
     * @throws Exception
     */
    public static String rsaEncrypt(String txt, String publicKey, String encoding) throws Exception {
        return rsaEncrypt(RSA, txt, publicKey, encoding);
    }
    
    /**
     * RSA私钥分段加密
     * @param data 要加密的明文
     * @param privateKey rsa私钥
     * @param blockLen 分段加密的块大小
     * @return base64编码后的密文字符串
     * @throws Exception 加密异常
     */
    public static byte[] rsaEncryptDataByPrivateKey(String algorithm, byte[] data, byte[] privateKey, int blockLen)
            throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, KeyFactory.getInstance(RSA).generatePrivate(new PKCS8EncodedKeySpec(privateKey)));
        return segmentCipher(cipher, data, blockLen);
    }

    /**
     * RSA私钥分段加密
     * @param data 要加密的明文
     * @param privateKey rsa私钥
     * @param blockLen 分段加密的块大小
     * @return base64编码后的密文字符串
     * @throws Exception 加密异常
     */
    public static byte[] rsaEncryptDataByPrivateKey(String algorithm, byte[] data, String privateKey, int blockLen)
            throws Exception {
        return rsaEncryptDataByPrivateKey(algorithm, data, base64Decode(privateKey), blockLen);
    }

    /**
     * RSA私钥分段加密
     * @param data 要加密的明文
     * @param privateKey rsa私钥
     * @return base64编码后的密文字符串
     * @throws Exception 加密异常
     */
    public static byte[] rsaEncryptDataByPrivateKey(byte[] data, String privateKey)
            throws Exception {
        return rsaEncryptDataByPrivateKey(RSA, data, base64Decode(privateKey), RSA_ENCRYPT_BLOCK_LEN);
    }

    /**
     * RSA私钥分段加密（使用1024位密钥），RSA摘要+Base64编码
     * @param data 要加密的明文
     * @param privateKey rsa私钥
     * @return base64编码后的密文字符串
     * @throws Exception 加密异常
     */
    public static String rsaEncryptByPrivateKey(byte[] data, String privateKey)
            throws Exception {
        byte[] cipherData = rsaEncryptDataByPrivateKey(RSA, data, base64Decode(privateKey), RSA_ENCRYPT_BLOCK_LEN);
        return base64EncodeString(cipherData);
    }

    /**
     * RSA私钥分段加密，对数据做Base64编码
     * @param txt 要加密的明文
     * @param privateKey rsa私钥
     * @param encoding 编码格式
     * @param blockLen 分段加密的块大小
     * @return base64编码后的密文字符串
     * @throws Exception 加密异常
     */
    public static String rsaEncryptByPrivateKey(String algorithm, String txt, String privateKey, String encoding, int blockLen)
            throws Exception {
        byte[] data = rsaEncryptDataByPrivateKey(algorithm, txt.getBytes(encoding), base64Decode(privateKey), blockLen);
        return base64EncodeString(data);
    }

    /**
     * RSA私钥加密（使用1024位密钥）
     * @param algorithm 加密算法
     * @param txt 要加密的铭文
     * @param privateKey RSA私钥
     * @param encoding 编码
     * @return 加密后的密文
     * @throws Exception 异常
     */
    public static String rsaEncryptByPrivateKey(String algorithm, String txt, String privateKey, String encoding) throws Exception {
        return rsaEncryptByPrivateKey(algorithm, txt, privateKey, encoding, RSA_ENCRYPT_BLOCK_LEN);
    }

    /**
     * RSA私钥加密（使用1024位密钥）
     * @param txt 要加密的铭文
     * @param privateKey RSA私钥
     * @return 加密后的密文
     * @throws Exception 异常
     */
    public static String rsaEncryptByPrivateKey(String txt, String privateKey, String encoding) throws Exception {
        return rsaEncryptByPrivateKey(RSA, txt, privateKey, encoding);
    }

    /**
     * RSA私钥解密
     * @param algorithm 加密算法
     * @param cipherData 加密数据
     * @param privateKey rsa私钥
     * @param blockLen 加密块大小
     * @return 解密后的明文数据
     * @throws Exception 解密失败
     */
    public static byte[] rsaDecryptData(String algorithm, byte[] cipherData, byte[] privateKey, int blockLen) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, KeyFactory.getInstance(RSA).generatePrivate(new PKCS8EncodedKeySpec(privateKey)));
        return segmentCipher(cipher, cipherData, blockLen);
    }
    
    /**
     * RSA私钥解密
     * @param algorithm 加密算法
     * @param cipherData 加密数据
     * @param privateKey rsa私钥
     * @param blockLen 加密块大小
     * @return 解密后的明文数据
     * @throws Exception 解密失败
     */
    public static byte[] rsaDecryptData(String algorithm, byte[] cipherData, String privateKey, int blockLen) throws Exception {
        return rsaDecryptData(algorithm, cipherData, base64Decode(privateKey), blockLen);
    }
    
    /**
     * RSA私钥解密
     * @param cipherData 加密数据
     * @param privateKey rsa私钥
     * @param blockLen 加密块大小
     * @return 解密后的明文数据
     * @throws Exception 解密失败
     */
    public static byte[] rsaDecryptData(byte[] cipherData, String privateKey, int blockLen) throws Exception {
        return rsaDecryptData(RSA, cipherData, base64Decode(privateKey), blockLen);
    }
    
    /**
     * RSA私钥解密
     * @param cipherData 加密数据
     * @param privateKey rsa私钥
     * @return 解密后的明文数据
     * @throws Exception 解密失败
     */
    public static byte[] rsaDecryptData(byte[] cipherData, String privateKey) throws Exception {
        return rsaDecryptData(RSA, cipherData, base64Decode(privateKey), RSA_DECRYPT_BLOCK_LEN);
    }
    
    /**
     * RSA私钥解密
     * @param algorithm 加密算法
     * @param cipherTxt 加密文本
     * @param privateKey rsa私钥
     * @param blockLen 加密块大小
     * @return 解密后的明文数据
     * @throws Exception 解密失败
     */
    public static byte[] rsaDecryptData(String algorithm, String cipherTxt, String privateKey, int blockLen) throws Exception {
        return rsaDecryptData(algorithm, base64Decode(cipherTxt), privateKey, blockLen);
    }
    
    /**
     * RSA私钥解密(1024位密钥)
     * @param algorithm 加密算法
     * @param cipherTxt 加密文本
     * @param privateKey rsa私钥
     * @return 解密后的明文数据
     * @throws Exception 解密失败
     */
    public static byte[] rsaDecryptData(String algorithm, String cipherTxt, String privateKey) throws Exception {
        return rsaDecryptData(algorithm, cipherTxt, privateKey, RSA_DECRYPT_BLOCK_LEN);
    }

    /**
     * RSA私钥解密(1024位密钥)
     * @param algorithm 加密算法
     * @param cipherTxt 加密文本
     * @param privateKey rsa私钥
     * @return 解密后的明文数据
     * @throws Exception 解密失败
     */
    public static byte[] rsaDecryptData2048(String algorithm, String cipherTxt, String privateKey) throws Exception {
        return rsaDecryptData(algorithm, cipherTxt, privateKey, RSA_DECRYPT_BLOCK_LEN_2048);
    }

    /**
     * RSA私钥解密
     * @param algorithm 加密算法
     * @param cipherTxt 加密文本
     * @param privateKey rsa私钥
     * @param encoding 密文编码格式
     * @param blockLen 加密块大小
     * @return 解密后的明文
     * @throws Exception 解密失败
     */
    public static String rsaDecrypt(String algorithm, String cipherTxt, String privateKey, String encoding, int blockLen) throws Exception {
        byte[] data = rsaDecryptData(algorithm, cipherTxt, privateKey, blockLen);
        return new String(data, encoding);
    }

    /**
     * RSA私钥解密(1024位密钥)
     * @param algorithm 加密算法
     * @param cipherTxt 加密文本
     * @param privateKey rsa私钥
     * @param encoding 密文编码格式
     * @return 解密后的明文
     * @throws Exception 解密失败
     */
    public static String rsaDecrypt(String algorithm, String cipherTxt, String privateKey, String encoding) throws Exception {
        return rsaDecrypt(algorithm, cipherTxt, privateKey, encoding, RSA_DECRYPT_BLOCK_LEN);
    }

    /**
     * RSA私钥解密(2048位密钥)
     * @param algorithm 加密算法
     * @param cipherTxt 加密文本
     * @param privateKey rsa私钥
     * @param encoding 密文编码格式
     * @return 解密后的明文
     * @throws Exception 解密失败
     */
    public static String rsaDecrypt2048(String algorithm, String cipherTxt, String privateKey, String encoding) throws Exception {
        return rsaDecrypt(algorithm, cipherTxt, privateKey, encoding, RSA_DECRYPT_BLOCK_LEN_2048);
    }

    /**
     * RSA私钥解密（使用默认的RSA算法）
     * @param cipherTxt 加密文本
     * @param privateKey rsa私钥
     * @param encoding 密文编码格式
     * @param blockLen 加密块大小
     * @return 解密后的明文
     * @throws Exception 解密失败
     */
    public static String rsaDecrypt(String cipherTxt, String privateKey, String encoding, int blockLen) throws Exception {
        return rsaDecrypt(RSA, cipherTxt, privateKey, encoding, blockLen);
    }

     /**
     * RSA私钥解密（1024位证书）
     * @param cipherTxt 加密文本
     * @param privateKey rsa私钥
     * @param encoding 密文编码格式
     * @return
     * @throws Exception
     */
    public static String rsaDecrypt(String cipherTxt, String privateKey, String encoding) throws Exception {
        return rsaDecrypt(cipherTxt, privateKey, encoding, RSA_DECRYPT_BLOCK_LEN);
    }

    /**
     * RSA私钥解密（2048位证书）
     * @param cipherTxt 加密文本
     * @param privateKey rsa私钥
     * @param encoding 密文编码格式
     * @return
     * @throws Exception
     */
    public static String rsaDecrypt2048(String cipherTxt, String privateKey, String encoding) throws Exception {
        return rsaDecrypt(cipherTxt, privateKey, encoding, RSA_DECRYPT_BLOCK_LEN_2048);
    }
    
    /**
     * RSA公钥解密
     * @param cipherData RSA密文
     * @param publicKey RSA公钥
     * @return 解密后的明文
     * @throws Exception 异常
     */
    public static byte[] rsaDecryptByPublicKey(byte[] cipherData, byte[] publicKey, int blockLen) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, KeyFactory.getInstance(RSA).generatePublic(new X509EncodedKeySpec(publicKey)));
        return segmentCipher(cipher, cipherData, blockLen);
    }
    
    /**
     * RSA公钥解密
     * @param cipherData RSA密文
     * @param publicKey RSA公钥
     * @return 解密后的明文
     * @throws Exception 异常
     */
    public static byte[] rsaDecryptByPublicKey(byte[] cipherData, byte[] publicKey) throws Exception {
        return rsaDecryptByPublicKey(cipherData, publicKey, RSA_DECRYPT_BLOCK_LEN);
    }
    
    /**
     * RSA公钥解密
     * @param cipherData RSA密文
     * @param publicKey RSA公钥
     * @return 解密后的明文
     * @throws Exception 异常
     */
    public static byte[] rsaDecryptByPublicKey(byte[] cipherData, String publicKey) throws Exception {
        return rsaDecryptByPublicKey(cipherData, base64Decode(publicKey));
    }
    
    /**
     * RSA公钥解密
     * @param cipherTxt RSA密文
     * @param publicKey RSA公钥
     * @return 解密后的明文
     * @throws Exception 异常
     */
    public static byte[] rsaDecryptByPublicKey(String cipherTxt, byte[] publicKey, int blockLen) throws Exception {
        return rsaDecryptByPublicKey(base64Decode(cipherTxt), publicKey, blockLen);
    }
    
    
    /**
     * RSA公钥解密
     * @param cipherTxt RSA密文
     * @param publicKey RSA公钥
     * @return 解密后的明文
     * @throws Exception 异常
     */
    public static byte[] rsaDecryptByPublicKey(String cipherTxt, String publicKey, int blockLen) throws Exception {
        return rsaDecryptByPublicKey(cipherTxt, base64Decode(publicKey), blockLen);
    }

    /**
     * RSA公钥解密(1024位证书)
     * @param cipherTxt RSA密文
     * @param publicKey RSA公钥
     * @return 解密后的明文
     * @throws Exception 异常
     */
    public static byte[] rsaDecryptByPublicKey(String cipherTxt, String publicKey) throws Exception {
        return rsaDecryptByPublicKey(cipherTxt, publicKey, RSA_DECRYPT_BLOCK_LEN);
    }

    /**
     * RSA公钥解密(2048位证书)
     * @param cipherTxt RSA密文
     * @param publicKey RSA公钥
     * @return 解密后的明文
     * @throws Exception 异常
     */
    public static byte[] rsaDecryptByPublicKey2048(String cipherTxt, String publicKey) throws Exception {
        return rsaDecryptByPublicKey(cipherTxt, publicKey, RSA_DECRYPT_BLOCK_LEN_2048);
    }

    /**
     * RSA签名
     * @param algorithm 签名算法
     * @param txt   要签名的文本
     * @param privateKey rsa私钥
     * @param encoding 编码
     * @return RSA签名
     */
    public static byte[] rsaSignData(String algorithm, String txt, String privateKey, String encoding) throws Exception {
        Signature signature = Signature.getInstance(algorithm);
        signature.initSign(KeyFactory.getInstance(RSA).generatePrivate(new PKCS8EncodedKeySpec(base64Decode(privateKey))));
        signature.update(txt.getBytes(encoding));
        return signature.sign();
    }

    /**
     * RSA签名
     * @param algorithm 签名算法
     * @param txt   要签名的文本
     * @param privateKey rsa私钥
     * @param encoding 编码
     * @return Base64Encode形式的RSA签名
     */
    public static String rsaSign(String algorithm, String txt, String privateKey, String encoding) throws Exception {
        return base64EncodeString(rsaSignData(algorithm, txt, privateKey, encoding));
    }
    
    /**
     * RSA签名，使用默认的SHA1withRSA算法
     * @param txt   要签名的文本
     * @param privateKey rsa私钥
     * @param encoding 编码
     * @return
     */
    public static String rsaSign(String txt, String privateKey, String encoding) throws Exception {
        return rsaSign(SHA1_WITH_RSA, txt, privateKey, encoding);
    }
    
    /**
     * RSA签名，使用UTF8编码
     * @param txt   要签名的文本
     * @param privateKey rsa私钥
     * @return
     */
    public static String rsaSign(String txt, String privateKey) throws Exception {
        return rsaSign(txt, privateKey, DFLT_ENCODING);
    }
    
    /**
     * 校验rsa签名
     * @param algorithm 签名算法
     * @param txt 文本信息
     * @param sign 签名
     * @param publicKey rsa公钥
     * @param encoding 编码格式
     * @return
     * @throws Exception
     */
    public static boolean rsaVerifyData(String algorithm, String txt, byte[] sign, String publicKey, String encoding) throws Exception {
        Signature signature = Signature.getInstance(algorithm);
        signature.initVerify(KeyFactory.getInstance(RSA).generatePublic(new X509EncodedKeySpec(base64Decode(publicKey))));
        signature.update(txt.getBytes(encoding));
        return signature.verify(sign);
    }

    /**
     * 校验rsa签名
     * @param algorithm 签名算法
     * @param txt 文本信息
     * @param sign 签名
     * @param publicKey rsa公钥
     * @param encoding 编码格式
     * @return
     * @throws Exception
     */
    public static boolean rsaVerify(String algorithm, String txt, String sign, String publicKey, String encoding) throws Exception {
        return rsaVerifyData(algorithm, txt, base64Decode(sign), publicKey, encoding);
    }
    
    /**
     * 校验rsa签名
     * @param txt 文本信息
     * @param sign 签名
     * @param publicKey rsa公钥
     * @param encoding 编码格式
     * @return
     * @throws Exception
     */
    public static boolean rsaVerify(String txt, String sign, String publicKey, String encoding) throws Exception {
        return rsaVerify(SHA1_WITH_RSA, txt, sign, publicKey, encoding);
    }
    
    /**
     * RSA验签
     * @param txt rsa明文
     * @param sign rsa签名
     * @param publicKey rsa公钥
     * @return
     * @throws Exception
     */
    public static boolean rsaVerify(String txt, String sign, String publicKey) throws Exception {
        return rsaVerify(txt, sign, publicKey, DFLT_ENCODING);
    }


    /**
     * 对文本信息进行urlEncode
     * @param txt 文本信息
     * @param encoding 编码
     * @return
     */
	public static String urlEncode(String txt, String encoding) {
	    try {
            return notBlank(txt) ? URLEncoder.encode(txt, encoding) : txt;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
	}
	
	/**
	 * urlEncode，默认使用utf8编码
	 * @param txt 文本
	 * @return
	 */
	public static String urlEncode(String txt) {
	    return urlEncode(txt, DFLT_ENCODING);
	}
	
	/**
	 * urlDecode
	 * @param txt 文本
	 * @param encoding 编码
	 * @return
	 */
	public static String urlDecode(String txt, String encoding) {
        try {
            return notBlank(txt) ? URLDecoder.decode(txt, encoding) : txt;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
	
	/**
	 * urlDecode，默认使用utf8编码
	 * @param txt 文本
	 * @return
	 */
	public static String urlDecode(String txt) {
	    return urlDecode(txt, DFLT_ENCODING);
	}
    
	/**
	 * 转换字符串编码
	 * @param s 要转换的字符串
	 * @param srcEncoding 原始编码 
	 * @param dstEncoding 目标编码
	 */
	public static String transferEncoding(String s, String srcEncoding, String dstEncoding) {
	    try {
            return new String(s.getBytes(srcEncoding), dstEncoding);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
	}
	
	/** 转换字符编码: ISO -> UTF8 */
	public static String iso2UTF8(String s) {
	    return StringUtils.newStringUtf8(StringUtils.getBytesIso8859_1(s));
	}
	
	/**
	 * 解码unicode字符串
	 * @param str str
	 * @return str-decoded
	 */
	public static String unicodeToString(String str) {
        Matcher matcher = UNICODE_PATTERN.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), String.valueOf(ch));
        }
        
        return str;
    }

    /**
     * 解码字符串中的十六进制字符
     * @param str str
     * @return str-decoded
     */
    public static String hexToString(String str, String encoding) {
        Matcher matcher = HEX_PATTERN.matcher(str);
        String hex;
        Pattern replaceP = compile("\\\\0*x");
        while (matcher.find()) {
            hex = matcher.group(1);
            str = str.replace(hex, hexDecodeString(replaceP.matcher(hex).replaceAll(""), encoding));
        }

        return str;
    }

	/**
	 * rsa密钥容器，提供密钥加载接口
	 * @author devin.xu
     * @date 2017.08
	 */
	public static class RsaKeyContainer {
	    
	    /** 对方的rsa公钥，用于加密发送给对方的消息 */
	    private String publicKey;
	    
	    /** 我方的rsa私钥，用于解密对方消息或者生成签名 */
	    private String privateKey;
	    
	    /**
	     * 从classpath中的资源文件加载
	     * @param path 路径
	     * @return 密钥文本
	     */
	    private String loadFromResource(String path) throws IOException {
	        InputStream ins = BaseUtil.getResourceAsStream(path);
	        if (null == ins) {
	            throw new FileNotFoundException("密钥文件不存在:" + path);
	        }
	        
	        return loadRsaKey(ins);
	    }

	    /**
	     * 公钥resource注入接口
	     * @param name classpath下的文件位置
	     * @throws IOException 文件读取异常
	     */
	    public void setPubKeyResource(String name) throws IOException {
	        this.publicKey = loadFromResource(name);
	    }
	    
	    /**
	     * 私钥resource注入接口
	     * @param name classpath下的文件位置
	     * @throws IOException 文件读取异常
	     */
	    public void setPriKeyResource(String name) throws IOException {
	        this.privateKey = loadFromResource(name);
	    }
	    
	    /**
	     * 公钥文件注入接口
	     * @param filepath 文件路径
	     * @throws IOException 文件读取异常
	     */
	    public void setPubKeyFile(String filepath) throws IOException {
	        this.publicKey = loadRsaKeyFromFile(new File(filepath));
	    }
	    
	    /**
	     * 私钥文件注入接口
	     * @param filepath 文件路径
	     * @throws IOException 文件读取异常
	     */
	    public void setPriKeyFile(String filepath) throws IOException {
	        this.privateKey = loadRsaKeyFromFile(new File(filepath));
	    }

	    // get&set
	    public String getPublicKey() {
	        return publicKey;
	    }

	    public void setPublicKey(String publicKey) {
	        this.publicKey = publicKey;
	    }

	    public String getPrivateKey() {
	        return privateKey;
	    }

	    public void setPrivateKey(String privateKey) {
	        this.privateKey = privateKey;
	    }
	}
}
