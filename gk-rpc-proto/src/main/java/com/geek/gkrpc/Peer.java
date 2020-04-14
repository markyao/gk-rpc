package com.geek.gkrpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 网络传输端点
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Peer {
    /**
     * 服务地址
     */
    private String host;
    /**
     * 端口
     */
    private int port;
}
