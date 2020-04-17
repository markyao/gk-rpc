package com.smgeek.gkrpc.server;

import com.geek.gkrpc.Request;
import com.geek.gkrpc.Response;
import com.smgeek.gkrpc.codec.Decoder;
import com.smgeek.gkrpc.codec.Encoder;
import com.smgeek.gkrpc.common.utils.ReflectionUtils;
import com.smgeek.gkrpc.transport.RequestHandler;
import com.smgeek.gkrpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

@Slf4j
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
        this.transportServer.init(config.getPort(), this.handler);

        this.encoder = ReflectionUtils.newInstance(config.getEncoder());
        this.decoder = ReflectionUtils.newInstance(config.getDecoder());

        serviceManager = new ServiceManager();
        serviceInvoker = new ServiceInvoker();
    }

    public <T> void register(Class<T> interfaceClass, T bean) {
        serviceManager.register(interfaceClass, bean);
    }

    public void start() {
        transportServer.start();
    }


    public void stop() {
        transportServer.stop();
    }

    private RequestHandler handler = (receive, toResp) -> {

        Response response = new Response();
        try {
            byte[] inBytes = IOUtils.readFully(receive, receive.available());
            Request request = decoder.decode(inBytes, Request.class);
            log.info("get request: {}", request);
            ServiceInstance serviceInstance = serviceManager.lookup(request);
            Object ret = serviceInvoker.invoke(serviceInstance, request);
            response.setData(ret);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            response.setCode(1);
            response.setMessage("RpcServer got error :"
                    + e.getClass().getName()
                    + ":" + e.getMessage());
        } finally {
            try {
                byte[] outBytes = encoder.encode(response);
                toResp.write(outBytes);
                log.info("Response client");
            } catch (IOException e) {
                log.warn(e.getMessage(), e);
            }
        }
    };
}
