import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 * @author huqinsong
 * @date 2018/4/11
 */
public class TestTest {

    @Before
    public void bf(){
        System.out.println("Before");
    }

    @BeforeClass
    public static void bfc(){
        System.out.println("BeforeClass");
    }

    @After
    public void af(){
        System.out.println("After");
    }

    @AfterClass
    public static void afc(){
        System.out.println("AfterClass");
    }

    @Test
    public void test(){
        System.out.println("test");
        Assert.isTrue( 1==1);
    }

    @Test
    public void test2(){
        System.out.println("test2");
        assert 1==1;
    }

}
