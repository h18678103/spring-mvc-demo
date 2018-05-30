/**
 * @author huqinsong
 * @date 2018/5/28
 */
public class CopyTestEntity implements Cloneable {

    public int i = 1;
    public String s = "abc";
    public CopyTestEntity2 copyTestEntity2 = CopyTestEntity2.getInst();

    private CopyTestEntity() {
        copyTestEntity2.i = 100;
        copyTestEntity2.s = "ggggg";
    }

    public static CopyTestEntity getInst(){
        return new CopyTestEntity();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
