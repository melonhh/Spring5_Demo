package cn.melon.demo;

public class LifeBean {
    private String name;

    public LifeBean() {
        System.out.println("LifeBean() 构造函数");
    }

    public void init() {
        System.out.println("this is init()");
    }

    public void destroy() {
        System.out.println("this is destroy()");
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
