package javatest;

import java.util.UUID;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * @author huqinsong
 * @date 2018/12/27
 */
public class T4 {
    public static final String UTF8    = "UTF-8";
    public static void main(String[] args) {
        // xml文档
        Document doc = DocumentHelper.createDocument();
        doc.setXMLEncoding(UTF8);
        Element root = doc.addElement("root");
        root.addNamespace("addNamespace", "2");
        root.addAttribute("addAttribute", "3");
        root.addText("rootText");
        Element addElement1 = root.addElement("addElement1").addText("addElement1Text");
        addElement1.addElement("addElement2").addText("addElement2Text");
        System.out.println(doc.asXML());
    }
}
