package cn.melon.demo;

import java.util.HashMap;
import java.util.Map;

public class CarStaticFactory {
    private static Map<Integer, A> map = new HashMap<>();

    static {
        map.put(1,new A());
        map.put(2,new A());
        map.put(3,new A());
    }

    public static A getCar(int id) {
        return map.get(id);
    }
}
