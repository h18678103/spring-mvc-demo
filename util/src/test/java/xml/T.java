package xml;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * @author huqinsong
 * @date 2018/8/10
 */
public class T {
    public static Element parseText(String text) {
        try {
            return DocumentHelper.parseText(text).getRootElement();
        } catch (DocumentException var2) {
            var2.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {


        String rspStr = "<response>" +
                "<envelope>" +
                "<head>" +
                "<version>v1.0.7.6</version>" +
                "<charset>UTF-8</charset>" +
                "</head>" +
                "<body>" +
                "<mchtId>100020091219001</mchtId>" +
                "<mchtOrderNo>E20160701134433</mchtOrderNo>" +
                "<responseCode>E0000</responseCode>" +
                "<responseMsg>正常</responseMsg>" +
                "<status>TX_BEGIN</status>" +
                "</body>" +
                "</envelope>" +
                "<sign>" +
                "<signType>1</signType>" +
                "<certificate/>" +
                "<signContent>JEothny3B1Gt27h+o6FShaQZP1CmKn4ZKC1DhXGiqsmQMsAo4JPmP+2w0UH3SV/9YZa0uJJdjWs7bJ4wUwcMtI4pVjOceHZvZYrAy5OzGSEML+p6yKvjg+YsLblbJiLl2QbZHJQN8dAvSQakpoGbCQ1ADbi/nLsLBUvpiU16lrxBrPw6erTdt2G2Pwh7GmLg53zytCQxdDu7IIqyRNSkYySbVgwMoauTKoh2Z60GZs0yXgPwE/GS7hHiQtl/heeEZb5x4gSdGYx/ZZ3ew+sfXnaLPNXV6H041YSqjZqtEmna5R7+q8kOb1dzx/eKQCiJl4RFOt69mE4wOg4StJRPTg==</signContent>" +
                "</sign>" +
                "</response>";

        Element response = parseText(rspStr);

        Element envelope = response.element("envelope");
        Element sign = response.element("sign");

        Element head = envelope.element("head");
        Element body = envelope.element("body");

        Element version = head.element("version");
        Element charset = head.element("charset");

        Element mchtId = body.element("mchtId");
        Element mchtOrderNo = body.element("mchtOrderNo");
        Element responseCode = body.element("responseCode");
        Element responseMsg = body.element("responseMsg");
        Element status = body.element("status");

        Element signType = sign.element("signType");
        Element signContent = sign.element("signContent");


        StringBuilder envelopeStr = new StringBuilder();
        envelopeStr.append("<envelope>");
        envelopeStr.append("<head><version>" + version.getText() + "</version>");
        envelopeStr.append("<charset>" + charset.getText() + "</charset></head>");
        envelopeStr.append("<body>");
        envelopeStr.append("<mchtId>").append(mchtId.getText()).append("</mchtId>");
        envelopeStr.append("<mchtOrderNo>").append(mchtOrderNo.getText()).append("</mchtOrderNo>");
        envelopeStr.append("<responseCode>").append(responseCode.getText()).append("</responseCode>");
        envelopeStr.append("<responseMsg>").append(responseMsg.getText()).append("</responseMsg>");
        envelopeStr.append("<status>").append(status.getText()).append("</status>");
        envelopeStr.append("</body>");
        envelopeStr.append("</envelope>");

        System.out.println(envelopeStr.toString());

    }
}
