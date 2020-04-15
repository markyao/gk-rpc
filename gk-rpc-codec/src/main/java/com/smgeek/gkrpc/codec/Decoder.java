package com.smgeek.gkrpc.codec;

/**
 * 反序列化接口
 */
public interface Decoder {

    <T> T decode(byte[] bytes, Class<T> clazz);
}
