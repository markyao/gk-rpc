package com.smgeek.gkrpc.codec;

import org.junit.Test;

import static org.junit.Assert.*;

public class JSONEncoderTest {

    @Test
    public void encode() {
        Encoder encoder = new JSONEncoder();
        TestBean bean = new TestBean();
        bean.setName("yaotailin");
        bean.setAge(34);
        byte[] encode = encoder.encode(bean);
        assertNotNull(encode);

    }
}