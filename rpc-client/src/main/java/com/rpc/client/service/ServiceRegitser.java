package com.rpc.client.service;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public interface ServiceRegitser {
    /**
     * 服务注册 服务名
     */
    void register(String serviceName, InetSocketAddress address);
}
