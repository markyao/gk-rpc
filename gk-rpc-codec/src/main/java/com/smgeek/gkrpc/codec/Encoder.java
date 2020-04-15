package com.smgeek.gkrpc.codec;

/**
 * 序列号接口
 */
public interface Encoder {

    byte[] encode(Object object);
}
