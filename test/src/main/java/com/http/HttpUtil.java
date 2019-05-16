package com.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Http工具
 * @author xulin
 * 2016年6月24日
 */
public class HttpUtil {

	private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);
	
	// 协议类型
	private static final String PROTOCOL_HTTPS = "https";
	
	/** 默认配置 */
	private static final HttpConfig dfltConfig = new HttpConfig();
	
	// ssl context
	private static SSLContext sslContext = null;
	
	// connection-factory
	private static SSLConnectionSocketFactory sslSocketFactory = null;
	
	public static void main(String[] args) throws IOException {
		
		HttpResult date =HttpUtil.doGet("https://guoji.qiandai.com/checkstand/time");
		System.out.println(date);
	}
	
	// 获取sslContext-单例初始化方法
    private static SSLContext getSslContext() throws KeyStoreException, KeyManagementException, NoSuchAlgorithmException {
        if (null != sslContext) {
            return sslContext;
        }
        
        synchronized (HttpUtil.class) {
            if (null == sslContext) {
                // 指定信任的密钥存储对象（信任任何链接）
                KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
                TrustStrategy anyTrustStrategy = new TrustStrategy() {
                    // 信任所有
                    @Override
                    public boolean isTrusted(X509Certificate[] x509Certificates, String s)
                            throws CertificateException {
                        return true;
                    }
                };
                
                sslContext = new SSLContextBuilder().loadTrustMaterial(trustStore, anyTrustStrategy).build();
            }
            
            return sslContext;
        }
    }
	
	// 获取connection-factory-单例初始化方法
	private static SSLConnectionSocketFactory getSslSocketFactory() throws KeyStoreException, KeyManagementException, NoSuchAlgorithmException {
	    if (null != sslSocketFactory) {
	        return sslSocketFactory;
	    }
	    
	    synchronized (HttpUtil.class) {
	        if (null == sslSocketFactory) {
	            sslSocketFactory = new SSLConnectionSocketFactory(getSslContext(),
	                    new String[] {"TLSv1", "TLSv1.1", "TLSv1.2"},
	                    null,
	                    SSLConnectionSocketFactory.getDefaultHostnameVerifier());
	        }
	        
	        return sslSocketFactory;
        }
	}
	
	// 初始化http-client
	private static CloseableHttpClient getHttpClient() {
		RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.<ConnectionSocketFactory>create();
		
		// 注册http套接字工厂
		ConnectionSocketFactory plainSF = new PlainConnectionSocketFactory();
		registryBuilder.register("http", plainSF);
		
		// 注册https套接字工厂
		try {
			registryBuilder.register("https", getSslSocketFactory());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		Registry<ConnectionSocketFactory> registry = registryBuilder.build();
		
		// 设置连接管理器(需要每次new，否则报Connection pool shut down异常)
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);
		
		// 构建客户端
		return HttpClientBuilder.create().setConnectionManager(connManager).build();
	}
	
	// 执行http request
	public static HttpResult doRequest(HttpRequestBase httpRequest, HttpConfig cfg, String charset) {
		// 使用默认参数
		if (null == cfg) {
		    cfg = dfltConfig;
		}
		
		// 设置超时时间
		Builder builder = RequestConfig.custom()
		        .setSocketTimeout(cfg.getSocketTimeout())
		        .setConnectTimeout(cfg.getConnTimeout());
		if (null != cfg.getBindIp()) {
		    builder.setLocalAddress(cfg.getBindIp());
		}
		
		httpRequest.setConfig(builder.build());
		
		// 执行
		CloseableHttpClient httpClient = getHttpClient();
		CloseableHttpResponse response = null;
		HttpResult httpResult = new HttpResult();
		
		try {
		    // 执行
			response = httpClient.execute(httpRequest);
			
			// 提取状态码和返回内容
			httpResult.setStatusCode(response.getStatusLine().getStatusCode());
			httpResult.setContent(EntityUtils.toString(response.getEntity(), charset));
		} catch (Exception e) {
			httpResult.setContent(e.toString());
			e.printStackTrace();
		} finally {
	        log.debug("url:<{}>, result:<{}>", httpRequest.getURI(), httpResult);
			BaseUtil.close(response, httpClient);
		}
		
		return httpResult;
	}

	// 发送get请求
	public static HttpResult doGet(String url, Map<String, ?> params, String charset) {
		// 拼接query string
	    String queryStr = BaseUtil.getSortedQueryStrWithUrlEncode(params);
	    if (StringUtils.isNotBlank(queryStr)) {
	        url = url + "?" + queryStr;
	    }

		HttpGet httpGet = new HttpGet(url);
		return doRequest(httpGet, null, charset);
	}
	
	public static HttpResult doGet(String url) {
		return doGet(url, null, BaseConsts.UTF8);
	}

	/** 发送post请求(表单参数) */
	public static HttpResult doPost(String url, Map<String, ?> params, String charset)  {
		HttpPost httpPost = new HttpPost(url);
		
		// 添加参数
		List<NameValuePair> nvps = new ArrayList<>();
		if (!BaseUtil.isEmpty(params)) {
			for(Map.Entry<String, ?> entry : params.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(),  String.valueOf(entry.getValue())));
			}
		}
		
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, Charset.forName(charset)));
		return doRequest(httpPost, null, charset);
	}
	
	/** 发送post请求(普通文本参数) */
	public static HttpResult doPost(String url, String data, HttpConfig cfg, String charset)  {
        HttpPost httpPost = new HttpPost(url);
        
        // 参数
        if (!BaseUtil.isBlank(data)) {
            httpPost.setEntity(new StringEntity(data, Consts.UTF_8));
        }
        
        return doRequest(httpPost, cfg, charset);
    }
	
    public static HttpResult doPost(String url, String data, String charset)  {
        return doPost(url, data, dfltConfig, charset);
    }
	
	/** 下载文件，会创建路径中缺少的目录 */
    public static boolean download(String downloadUrl, String savePath,
            Map<String, String> paraMap, HttpConfig cfg) {
        // url
        URL url = null;
        try {
            url = new URL(downloadUrl);
        } catch (MalformedURLException e) {
            log.error("url格式错误", e);
            return false;
        }
        
        // cfg
        if (null == cfg) {
            cfg = dfltConfig;
        }

        // connect & download
        PrintWriter writer = null;      // 写入参数
        InputStream ins = null;         // 从http connection中读取
        File file = new File(savePath); // 保存的文件
        
        try {
            // 创建目录
            FileUtils.forceMkdirParent(file);
            
            // 设置超时
            URLConnection conn = getDownloadConn(url);  // 不需要close
            conn.setConnectTimeout(cfg.getConnTimeout());
            conn.setReadTimeout(cfg.getReadTimeout());
            conn.setDoInput(true);
            
            // 写入参数
            if (!BaseUtil.isEmpty(paraMap)) {
                conn.setDoOutput(true);
                writer = new PrintWriter(conn.getOutputStream());
                writer.print(BaseUtil.getSortedQueryStrWithUrlEncode(paraMap));
                writer.flush();
            }
            
            // 保存到文件（会关闭流）
            ins = conn.getInputStream();
            FileUtils.copyToFile(ins, file);
            return true;
        } catch (Exception e) {
            log.error("HTTP下载异常", e);
        } finally {
            BaseUtil.close(writer, ins);
        }
        
        return false;
    }
    
    // 获取下载链接（分开处理http/https）
    private static URLConnection getDownloadConn(URL url) throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException, IOException {
        URLConnection conn = url.openConnection();
        
        // http
        boolean isHttps = PROTOCOL_HTTPS.equals(url.getProtocol().toLowerCase());
        if (!isHttps) {
            return conn;
        }
        
        // https
        HttpsURLConnection httpsConn = (HttpsURLConnection)conn;
        httpsConn.setSSLSocketFactory(getSslContext().getSocketFactory());
//        httpsConn.setHostnameVerifier(new TrustAnyHostnameVerifier());
        return httpsConn;
    }
    
    public static boolean download(String downloadUrl, String savePath,
            Map<String, String> paraMap) {
        return download(downloadUrl, savePath, paraMap, null);
    }
    
    // 下载不带参数+默认缓存
    public static boolean download(String downloadUrl, String savePath) {
        return download(downloadUrl, savePath, null);
    }

    /**
     * 文件流上传
     * @param uploadUrl 上传url
     * @param filepath  本地文件路径
     * @param cfg   http配置
     * @return
     */
    public static boolean upload(String uploadUrl, String filepath, HttpConfig cfg) {
        // url
        URL url = null;
        try {
            url = new URL(uploadUrl);
        } catch (MalformedURLException e) {
            log.error("url格式错误", e);
            return false;
        }
        
        // file
        File file = new File(filepath);
        if (!file.exists()) {
            log.error("文件不存在， 路径: ", file.getAbsolutePath());
            return false;
        }
        
        // cfg
        if (null == cfg) {
            cfg = dfltConfig;
        }

        // connect & upload
        InputStream ins = null;     // 从文件读入
        OutputStream out = null;    // 向http connection写入
        
        try {
            // 设置超时
            URLConnection conn = url.openConnection();  // 不需要close
            conn.setConnectTimeout(cfg.getConnTimeout());
            conn.setReadTimeout(cfg.getReadTimeout());
            conn.setDoInput(true);
            conn.setDoOutput(true);
            
            // 上传数据
            ins = new FileInputStream(file);
            out = IOUtils.buffer(conn.getOutputStream());
            IOUtils.copy(ins, out); // 默认缓冲=4k
            out.flush();
            return true;
        } catch (Exception e) {
            log.error("HTTP上传异常", e);
        } finally {
            BaseUtil.close(out, ins);
        }
        
        return false;
    }

    public static boolean upload(String uploadUrl, String filepath) {
        return upload(uploadUrl, filepath, null);
    }
}