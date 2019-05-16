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



    @Test
    public void quickPayTest(){
        Map<String, String> param = new HashMap<>();
        param.put("merchantId", "10154414");
        param.put("outTradeNo", System.currentTimeMillis()+"");
        param.put("payMoney", "123456");
        param.put("returnUrl", "/mct/returnUrl");
        param.put("notifyUrl", "/mct/notify");
        param.put("goodsName", "IphoneX");
        param.put("goodsDesc", "白色*，2台");
        param.put("ip", "192.168.1.102");

        String sign = BaseUtil.getSign(param, "104A0F01C96A4B81B7507D0D92BD1C16");
        param.put("sign", sign);

        // 请求参数
        System.out.println(BaseUtil.toJson(param));
        String url = "http://api.mixpay.test/pay/usr/prepay.action";
        HttpResult httpResult = HttpUtil.doPost(url, param, "UTF-8");
        System.out.println(httpResult);
    }

    @Test
    public void t(){
        System.out.println(new PayTest() instanceof PayTest);
    }


}
