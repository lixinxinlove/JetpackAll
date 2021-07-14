package com.lixinxinlove.all.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class WangmaiLiProxy implements InvocationHandler {

    private Girl girl;


    public WangmaiLiProxy(Girl girl) {
        super();
        this.girl = girl;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        do1();
        Object obj = method.invoke(girl, args);
        do2();
        return obj;
    }


    private void do1() {
        System.out.println("增强前");
    }

    private void do2() {
        System.out.println("增强后");
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(girl.getClass().getClassLoader(), girl.getClass().getInterfaces(), this);
    }

}
