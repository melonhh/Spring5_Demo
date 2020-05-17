package cn.melon;

import cn.melon.aop.AopTest;
import cn.melon.demo.*;
import cn.melon.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
    private ApplicationContext applicationContext = null;

    @Before
    public void before() {
        applicationContext = new ClassPathXmlApplicationContext("applicationConfig.xml");
    }

    @Test
    public void test1() {
        A a = (A) applicationContext.getBean("a");
        System.out.println(a.getB().getName());

        B b = (B) applicationContext.getBean("b");
        System.out.println(b.getA().getName());
    }

    @Test
    public void testLifBean() {
        LifeBean lifeBean = (LifeBean) applicationContext.getBean("lifeBean");
    }

    @Test
    public void testStaticFactory() {
        A bmw = (A) applicationContext.getBean("bmw");
        System.out.println(bmw);
    }

    @Test
    public void testAutowired() {
        AutowiredTest autowiredTest = (AutowiredTest) applicationContext.getBean("auto");
        System.out.println(autowiredTest.getA());
    }


    @Test
    public void testG() {
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.add();
    }

    @Test
    public void testAop() {
        AopTest aopTest = (AopTest) applicationContext.getBean("aopTestImpl");    // 必须使用接口
        aopTest.add(2, 2);
    }


}
