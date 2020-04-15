package com.smgeek.gkrpc.codec;

import org.junit.Test;

import static org.junit.Assert.*;

public class JSONDecoderTest {

    @Test
    public void decode() {
        Encoder encoder = new JSONEncoder();
        TestBean bean = new TestBean();
        bean.setName("yaotailin");
        bean.setAge(34);
        byte[] bytes = encoder.encode(bean);

        Decoder decoder = new JSONDecoder();
        TestBean testBean = decoder.decode(bytes, TestBean.class);
        assertEquals(34, testBean.getAge());
        assertEquals("yaotailin", testBean.getName());
    }
}