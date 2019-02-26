package javatest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Test {

    static String s = "iVBORw0KGgoAAAANSUhEUgAAAMAAAADACAYAAABS3GwHAAAAAXNSR0IArs4c6QAABqVJREFUeNrt3ctrFVccwHH" +
            "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";

    public static void main(String[] args) {
        String strImg = GetImageStr();
        System.out.println(strImg);
//        GenerateImage(s);
    }

    //图片转化成base64字符串
    public static String GetImageStr() {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        String imgFile = "D:\\tupian\\new.jpg";//待处理的图片
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }

    //base64字符串转化成图片
    public static boolean GenerateImage(String imgStr) {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            //生成jpeg图片
            String imgFilePath = "D:\\tupian\\new.jpg";//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
