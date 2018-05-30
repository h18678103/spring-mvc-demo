/**
 * @author huqinsong
 * @date 2018/5/28
 */
public class CopyTestEntity2 implements Cloneable {

    public int i = 1;
    public String s = "abc";

    private CopyTestEntity2() {
    }

    public static CopyTestEntity2 getInst(){
        return new CopyTestEntity2();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
