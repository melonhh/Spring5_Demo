package cn.melon.dao;

public class BaseDao<T> {
    public void add(T entity) {
        System.out.println("传过来的类是：" + entity);
    }
}
