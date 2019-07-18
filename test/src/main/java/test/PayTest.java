package test;

import com.http.BaseUtil;
import com.http.HttpResult;
import com.http.HttpUtil;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

/**
 * @author huqinsong
 * @date 2019/5/15
 */
public class PayTest {

// TEST
//  private String merchantId = "10154414";
////  private String host = "http://api.mixpay.test/";
//  private String sign = "104A0F01C96A4B81B7507D0D92BD1C16";

    // DEV
    private String merchantId = "10082832";
    private String host = "http://localhost:8080/mixpay-openapi-web/";
    private String sign = "2A264772F8674BE28604FBDF97F3874D";


    @Test
    public void quickPayTest() {
        Map<String, String> param = new HashMap<>();
        param.put("merchantId", merchantId);
        param.put("outTradeNo", System.currentTimeMillis() + "");
        param.put("payMoney", "123456");
        param.put("returnUrl", "/mct/returnUrl");
        param.put("notifyUrl", "/mct/notify");
        param.put("goodsName", "IphoneX");
        param.put("goodsDesc", "白色*，2台");
        param.put("ip", "192.168.1.102");
        param.put("payType", "10");

        param.put("sign", BaseUtil.getSign(param, sign));

        // 请求参数
        System.out.println(BaseUtil.toJson(param));

        String url = host + "pay/usr/prepay.action";
        HttpResult httpResult = HttpUtil.doPost(url, param, "UTF-8");
        System.out.println(httpResult);
    }

    @Test
    public void withdrawTest() {
        Map<String, String> param = new HashMap<>();
        param.put("merchantId", merchantId);
        param.put("outWithdrawNo", System.currentTimeMillis() + "");
        param.put("withdrawMoney", "123456");
        // 代付类型：1-余额代付(默认)/2-垫资(D0)
        param.put("withdrawType", "1");
        param.put("personName", "张老三");
        param.put("cardNo", "622252528284412368");
        param.put("bankNo", "BOC");
        param.put("sign", BaseUtil.getSign(param, sign));

        // 请求参数
        System.out.println(BaseUtil.toJson(param));

        String url = host + "pay/withdraw.do";
        HttpResult httpResult = HttpUtil.doPost(url, param, "UTF-8");
        System.out.println(httpResult);
    }




    @Test
    public void t() {
        System.out.println(null instanceof PayTest);
    }


}
