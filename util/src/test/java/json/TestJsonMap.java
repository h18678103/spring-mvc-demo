package json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author huqinsong
 * @date 2018/10/19
 */
public class TestJsonMap {

    public static void main(String[] args) {
        String json  = "{" +
                "                \"head\": {" +
                "                    \"version\": \"1.0\"," +
                "                    \"method\": \"sandpay.trade.precreate\"," +
                "                    \"productId\": \"00000012\"," +
                "                    \"accessType\": \"1\"," +
                "                    \"mid\": \"\"," +
                "                    \"plMid\": \"\"," +
                "                    \"channelType\": \"07\"," +
                "                    \"reqTime\": \"\"" +
                "                }," +
                "                \"body\": {" +
                "                    \"payTool\": \"0403\"," +
                "                    \"orderCode\": \"\"," +
                "                    \"limitPay\": \"\"," +
                "                    \"totalAmount\": \"\"," +
                "                    \"subject\": \"pay\"," +
                "                    \"body\": \"\"," +
                "                    \"txnTimeOut\": \"\"," +
                "                    \"storeId\": \"\"," +
                "                    \"terminalId\": \"\"," +
                "                    \"operatorId\": \"\"," +
                "                    \"notifyUrl\": \"\"," +
                "                    \"bizExtendParams\": \"\"," +
                "                    \"merchExtendParams\": \"\"," +
                "                    \"clearCycle\": \"\"," +
                "                    \"riskRateInfo\": \"\"," +
                "                    \"extend\": \"\"" +
                "                }" +
                "            }";
        JSONObject jsonObject = JSON.parseObject(json);
        System.out.println(jsonObject);
    }
    
}
