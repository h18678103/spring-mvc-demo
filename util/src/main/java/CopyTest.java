/**
 * @author huqinsong
 * @date 2018/5/28
 */
public class CopyTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        CopyTestEntity entity = CopyTestEntity.getInst();

        CopyTestEntity clone = (CopyTestEntity) entity.clone();
//        clone.i =2;
//        clone.s = "def";
//        clone.copyTestEntity2.i = 200;
//        clone.copyTestEntity2.s = "xxxxxxxxx";
        System.out.println(entity.i);
        System.out.println(entity.s);
        System.out.println(entity.copyTestEntity2.i);
        System.out.println(entity.copyTestEntity2.s);

        System.out.println(clone.i);
        System.out.println(clone.s);
        System.out.println(clone.copyTestEntity2.i);
        System.out.println(clone.copyTestEntity2.s);

        System.out.println(entity.hashCode());
        System.out.println(clone.hashCode());
    }

}
