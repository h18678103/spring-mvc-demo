package proxy;

/**
 * @author huqinsong
 * @date 2018/5/22
 */
public class ConcreteSellFisher implements SellFisher {

    @Override
    public int sellFish() {
        System.out.println("my fish is delicious!!");
        return 10;
    }

    @Override
    public int sellFish2() {
        System.out.println("my fish2 is delicious!!");
        return 20;
    }

}
