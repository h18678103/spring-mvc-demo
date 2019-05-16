package cn.com.sdf;

import cn.com.sdf.contants.TradeConstants;
import cn.com.sdf.enums.TradeApiContentFormatEnums;
import cn.com.sdf.enums.TradeApiMethodEnums;
import cn.com.sdf.util.IdWorker;
import cn.com.sdf.util.RsaUtil;
import cn.com.sdf.vo.request.biz.TradeB2CH5PayRequest;
import cn.com.sdf.vo.request.biz.TradeRequest;
import cn.com.sdf.vo.response.TradeResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.Test;

/**
 * (C) Shanghai Sand Information Technology System Co., Ltd.
 * All Rights Reserved.
 * <p>
 * Description: 商户入驻
 * <p>
 * Modification History: <p>
 * ============================================================================= <p>
 * Author         Date          Modification Description <p>
 * ------------- ---------- --------------------------------------------------- <p>
 * Wu.WQ         2018/10/23        Create <p>
 * ============================================================================= <p>
 */
public class TestSDFPay {

    private static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter yyMMdd = DateTimeFormatter.ofPattern("yyMMdd");
    private static final DateTimeFormatter yyyyMMddHHmmss = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private String url = "http://sdf.sandpay.tk:20113/gateway/api";
    private SDFPayClient SDFPayClient = new SDFPayClient(url);
    private String notifyUrl = "http://161.129.71.106:20048/demo/notify";
    private String returnUrl = "http://161.129.71.106:20048/demo/return";

    private String appId = "552018102914332"; // 代理商
    private String publicKey = "";
    private String privateKey = "";
    private String format = TradeApiContentFormatEnums.JSON.getValue();
    private String charset = TradeConstants.CHARSET;
    private String signType = TradeConstants.TRADE_API_SIGN_TYPE;
    private String timestamp = LocalDateTime.now().format(DATE_TIME);


    private String version = TradeConstants.VERSION;
    private String nonce = String.valueOf(System.currentTimeMillis());

    @Test
    public void test() {
        tradeH5();
    }

    private void tradeH5() {
        String method = TradeApiMethodEnums.TRADE_B2CPAY_H5.getMethod();

        String totalAmount = "0.01";

        String bizContent = TradeB2CH5PayRequest.builder()
                .totalAmount(totalAmount)
                .outOrderNo("SDF" + LocalDate.now().format(yyMMdd) + IdWorker.getId())
                .subject("无人超市")
                .body("零食")
                .createTime(LocalDateTime.now().format(yyyyMMddHHmmss))
                .timeOut(LocalDateTime.now().plusMinutes(30).format(yyyyMMddHHmmss))
                .createIp(getLocalAddress())
                .notifyUrl(notifyUrl)
                .returnUrl(returnUrl)
                .build().toJsonString();

        TradeResponse tradeResponse = doRequest(method, bizContent);
        System.out.println(tradeResponse);
        if ("200" .equals(tradeResponse.getCode())) {
            boolean check = RsaUtil.rsaCheckV2(tradeResponse.toMap(), publicKey, TradeConstants.CHARSET);
            System.out.println("验签结果：" + check);
        }
    }

    private TradeResponse doRequest(String method, String bizContent) {
        return SDFPayClient.execute(TradeRequest.builder().appId(appId)
                .format(format)
                .charset(charset)
                .method(method)
                .signType(signType)
                .timestamp(timestamp)
                .nonce(nonce)
                .version(version)
                .bizContent(bizContent).build().buildSign(privateKey));
    }

    public static String getLocalAddress() {
        String ip = "127.0.0.1";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }
}
