package cn.com.sdf.statictest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * @author huqinsong
 * @date 2019/5/17
 */
public class T2 {

    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        File file = new File("C:\\Users\\user003\\Desktop\\a.xml");
        Element root = new SAXReader().read(new FileInputStream(file)).getRootElement();
        String name = root.getName();
        String text = root.getText();
        System.out.println("name="+name+",\ttext="+text);
        Node ex = root.selectSingleNode("/ex");
        System.out.println("name="+ex.getName()+",\ttext="+ex.getText());
    }
}
