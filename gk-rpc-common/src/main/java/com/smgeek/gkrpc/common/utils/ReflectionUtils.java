package com.smgeek.gkrpc.common.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 反射工具类
 */
public class ReflectionUtils {

    /**
     * 根据class创建对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 获得class的public方法
     *
     * @param clazz
     * @return
     */
    public static Method[] getPublishMethod(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> collect = Arrays.asList(methods).stream()
                .filter(m -> Modifier.isPublic(m.getModifiers()))
                .collect(Collectors.toList());
        return collect.toArray(new Method[0]);

    }

    /**
     * 调用指定对象的指定方法
     * @param obj
     * @param method
     * @param args
     * @return
     */
    public static Object invoke(Object obj, Method method, Object... args) {
        try {
            return method.invoke(obj, args);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
