package com.segeek.gkrpc;

import com.smgeek.gkrpc.codec.Decoder;
import com.smgeek.gkrpc.codec.Encoder;
import com.smgeek.gkrpc.common.utils.ReflectionUtils;

import java.lang.reflect.Proxy;

public class RpcClient {
    private RpcClientConfig config;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RpcClient() {
        this(new RpcClientConfig());
    }

    public RpcClient(RpcClientConfig config) {
        this.config = config;
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());
        this.selector = ReflectionUtils.newInstance(config.getTransportSelectorClass());

        this.selector.init(
                this.config.getSevers(),
                this.config.getConnectCount(),
                this.config.getTransportClass()
        );
    }

    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{clazz},
                new RemoteInvoker(clazz, encoder, decoder, selector)
        );
    }
}
