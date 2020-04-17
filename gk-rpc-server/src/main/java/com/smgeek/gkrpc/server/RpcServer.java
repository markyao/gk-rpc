package com.smgeek.gkrpc.server;

import com.smgeek.gkrpc.codec.Decoder;
import com.smgeek.gkrpc.codec.Encoder;
import com.smgeek.gkrpc.common.utils.ReflectionUtils;
import com.smgeek.gkrpc.transport.TransportServer;

public class RpcServer {

    private RpcServerConfig config;
    private TransportServer transportServer;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    public RpcServer(RpcServerConfig config) {
        this.config = config;

        this.transportServer = ReflectionUtils.newInstance(config.getTransportServer());
        this.encoder = ReflectionUtils.newInstance(config.getEncoder());
        this.decoder = ReflectionUtils.newInstance(config.getDecoder());

        serviceManager = new ServiceManager();
        serviceInvoker = new ServiceInvoker();
    }
}
