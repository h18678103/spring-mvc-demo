package serializ;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author huqinsong
 * @date 2019/7/5
 */
public class SerDemo implements Serializable {
    public transient int age = 23;
    public String name;

    public SerDemo() {
        System.out.println("默认构造器。。。");
    }

    public SerDemo(String name) {
        this.name = name;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        System.out.println("writeObject");
        stream.defaultWriteObject();
        stream.writeInt(age);
    }

    private void readObject(ObjectInputStream stream) throws ClassNotFoundException, IOException {
        System.out.println("readObject");
        stream.defaultReadObject();
        age = stream.readInt();
    }

    @Override
    public String toString() {
        return "年龄：" + age + ",  姓名：" + name;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerDemo stu = new SerDemo("Ming");
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(stu);
        System.out.println("1");
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bout.toByteArray()));
        SerDemo stu1 = (SerDemo) in.readObject();
        System.out.println(stu1);
    }

}