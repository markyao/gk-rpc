package com.smgeek.gkrpc.common.utils;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ReflectionUtilsTest {

    @Test
    public void newInstance() {
        TestClass testClass = ReflectionUtils.newInstance(TestClass.class);
        assertNotNull(testClass);
    }

    @Test
    public void getPublishMethod() {
        Method[] methods = ReflectionUtils.getPublishMethod(TestClass.class);
        assertEquals(1, methods.length);
        String name = methods[0].getName();
        assertEquals("b", name);
    }

    @Test
    public void invoke() {
        Method[] methods = ReflectionUtils.getPublishMethod(TestClass.class);
        Method method = methods[0];
        TestClass testClass = new TestClass();
        Object r = ReflectionUtils.invoke(testClass, method);
        assertEquals("b", r);
    }
}