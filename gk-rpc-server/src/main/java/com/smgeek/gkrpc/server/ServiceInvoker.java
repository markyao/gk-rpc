package com.smgeek.gkrpc.server;

import com.geek.gkrpc.Request;
import com.smgeek.gkrpc.common.utils.ReflectionUtils;

/**
 * 调用服务
 */
public class ServiceInvoker {

    /**
     * @param serviceInstance
     * @param request
     * @return
     */
    public Object invoke(ServiceInstance serviceInstance, Request request) {
        return ReflectionUtils.invoke(serviceInstance.getTarget(), serviceInstance.getMethod(),
                request.getParameters());
    }
}
