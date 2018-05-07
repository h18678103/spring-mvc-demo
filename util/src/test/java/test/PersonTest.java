package test;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import sun.text.normalizer.UCharacter;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author huqinsong
 * @date 2018/5/7
 */
public class PersonTest {

    Person person = new Person();

    @Category(AttributeFun.class)
    @Test
    public void testGetAge(){
        int age = person.getAge();
        assertEquals(12, age);
    }
    @Category(AttributeFun.class)
    @Test
    public void testGetName(){
        String name = person.getName();
        assertEquals("Willard", name);
    }
    @Category(BehaviorFun.class)
    @Test
    public void testTalk(){
        String message = person.talkTo("Jimy");
        assertNotNull(message);
    }

    @Category(BehaviorFun.class)
    @Test(timeout=200)
    public void testWalk(){
        person.walk();
    }
}
