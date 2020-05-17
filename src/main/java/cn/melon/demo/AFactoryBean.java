package cn.melon.demo;

import org.springframework.beans.factory.FactoryBean;

public class AFactoryBean implements FactoryBean<A> {

    @Override
    public A getObject() throws Exception {
        return new A();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public Class<?> getObjectType() {
        return A.class;
    }
}
