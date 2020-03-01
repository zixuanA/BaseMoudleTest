package com.example.busutillibrary;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final public class BusUtil {
    Map<Class<?>, List<MethodCall>> methodCallMap = new HashMap<>();
    Map<Object, List<Class<?>>> unregisterMap = new HashMap<>();
    private volatile static BusUtil busUtil;

    private BusUtil() {
    }

    public static BusUtil getDefault() {
        if (busUtil == null) {
            synchronized (BusUtil.class) {
                if (busUtil == null) {
                    busUtil = new BusUtil();
                }
            }
        }
        return busUtil;
    }

    public void register(Object subscriber) {
        Class<?> clazz = subscriber.getClass();
        Method[] methods = clazz.getMethods();
        ArrayList<Object> events = new ArrayList<>();

        for (Method method : methods) {
            EventUtil eventUtil = method.getAnnotation(EventUtil.class);
            if (eventUtil != null) {
                MethodCall methodCall = new MethodCall(method, subscriber, eventUtil.threadModel());
                //这里可能会导致null pointer考虑怎么改动
                List<MethodCall> list = methodCallMap.get(method.getParameterTypes()[0]);
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(methodCall);
                events.add(method.getParameterTypes()[0]);
            }
        }

    }

    public void post(Object event){
        //TODO();
    }

    public void unregister(Object subscriber) {
        List<Class<?>> list = unregisterMap.get(subscriber);
        for (Class clazz : list) {
            List<MethodCall> methodCalls = methodCallMap.get(clazz);
            if (methodCalls == null) continue;

            for (int i = methodCalls.size(); i > 0; i--) {
                if (methodCalls.get(i).getObject() == subscriber) {
                    methodCalls.remove(i);
                }
            }
        }
    }
}
