package serializ;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 *
 * Externalizable接口继承自Serializable, 我们在实现该接口时，必须实现writeExternal()和readExternal()方法，
 * 而且只能通过手动进行序列化，并且两个方法是自动调用的，因此，这个序列化过程是可控的，可以自己选择哪些部分序列化
 * @author huqinsong
 * @date 2019/7/5
 */
public class Blip implements Externalizable {
    private int i ;
    private String s;
    public Blip() {}
    public Blip(String x, int a) {
        System.out.println("Blip (String x, int a)");
        s = x;
        i = a;
    }
    public String toString() {
        return s+i;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip.writeExternal");
        out.writeObject(s);
        out.writeInt(i);
    }
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip.readExternal");
        s = (String)in.readObject();
        i = in.readInt();
    }
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        System.out.println("Constructing objects");
        Blip b = new Blip("A Stirng", 47);
        System.out.println(b);
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("D://file1.txt"));
        System.out.println("保存对象");
        o.writeObject(b);
        o.close();
        //获得对象
        System.out.println("获取对象");
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("D://file1.txt"));
        System.out.println("Recovering b");
        b = (Blip)in.readObject();
        System.out.println(b);
    }


}
