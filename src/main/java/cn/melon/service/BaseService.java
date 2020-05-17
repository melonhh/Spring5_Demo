package cn.melon.service;

import cn.melon.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService<T> {
    @Autowired
    protected BaseDao<T> baseDao;

    public void add() {
        System.out.println("BaseService里的add方法：" + baseDao);
    }
}
