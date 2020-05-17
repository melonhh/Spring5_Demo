package cn.melon.aop;

import org.springframework.stereotype.Component;

@Component("aopTestImpl")
public class AopTestImpl implements AopTest{
    @Override
    public int add(int i, int j) {
        int rs = i + j;
        System.out.println("add rs:" + rs);
        return rs;
    }

    @Override
    public int sub(int i, int j) {
        int rs = i - j;
        System.out.println("sub rs:" + rs);
        return rs;
    }
}
