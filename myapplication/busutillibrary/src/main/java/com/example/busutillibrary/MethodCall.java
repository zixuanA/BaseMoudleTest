package com.example.busutillibrary;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodCall {
    private Method method;
    private Object object;
    private ThreadModel threadModel;
    public Object getObject(){
        return object;
    }

    public MethodCall(Method method,Object object,ThreadModel threadModel){
        this.method = method;
        this.object = object;
        this.threadModel = threadModel;
    }
    public void invoke(){
        try {
            method.invoke(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
