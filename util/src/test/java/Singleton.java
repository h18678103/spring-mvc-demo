/**
 * @author huqinsong
 * @date 2018/4/11
 */
public class Singleton {

   {
        System.out.println("ttttttttttttttttttt");
    }
    private static class SingletonHolder {
        private SingletonHolder(){
            System.out.println("SingletonHolder init");
        }
        private static final Singleton INSTANCE = new Singleton();
    }
    private Singleton (){
        System.out.println("Singleton init");
    }
    public static final Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
       Class.forName("Singleton").newInstance();
//        Singleton instance = Singleton.getInstance();
//        SingletonHolder singletonHolder = new SingletonHolder();
    }
}
