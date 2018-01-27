package com.frank.proxy.impl;
// 代理处理器：
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationHandler;
/**
 * @author frank
 * @version 1.0
 * @date 2018/1/27 0027 下午 4:58
 * 代理处理器
 */
@Slf4j
public class DynamicSubject implements InvocationHandler{

    private Object sub;
    public DynamicSubject() {}

    public DynamicSubject(Object obj) {
        sub = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info( " before calling method={},args={}",method,args);
        method.invoke(sub,args);
        log.info( " after calling method={},args={}",method,args);
        return  null ;
    }
}
