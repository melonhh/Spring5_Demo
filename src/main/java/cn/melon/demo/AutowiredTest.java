package cn.melon.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("auto")
public class AutowiredTest {
    @Autowired(required = false)
    private A a;

    public AutowiredTest() {
    }

    public A getA() {
        return a;
    }
}
