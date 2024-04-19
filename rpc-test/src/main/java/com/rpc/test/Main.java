package com.rpc.test;

import com.rpc.client.service.impl.ServiceRegisterImpl;

import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) {
        ServiceRegisterImpl service = new ServiceRegisterImpl();
        service.register("test",new InetSocketAddress("127.0.0.1",10086));

    }
}