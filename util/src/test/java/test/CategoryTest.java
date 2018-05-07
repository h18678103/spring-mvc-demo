package test;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author huqinsong
 * @date 2018/5/7
 */
@RunWith(Categories.class)
@Suite.SuiteClasses(PersonTest.class)
@Categories.IncludeCategory(BehaviorFun.class)
public class CategoryTest{
    //注意，如果不加@IncludeCategory注解，那么就和使用Suit具有一样的效果了。
}
