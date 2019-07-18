package nio;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import javax.swing.text.AbstractDocument;

/**
 * @author huqinsong
 * @date 2019/6/28
 */
public class IOTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filePath = "";
        // 写入文件（追加方式：StandardOpenOption.APPEND）
//        Files.write(Paths.get(filePath), AbstractDocument.Content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);

        // 读取文件
        byte[] data = Files.readAllBytes(Paths.get(filePath));
        System.out.println(new String(data, StandardCharsets.UTF_8));
        Thread t = new Thread();
        t.join();

    }
}
