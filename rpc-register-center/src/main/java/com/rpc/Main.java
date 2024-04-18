package com.rpc;

import com.lhc.register.service.impl.DefaultRegisterCenterFactoryImpl;

public class Main {
    public static void main(String[] args) {
        DefaultRegisterCenterFactoryImpl factory = new DefaultRegisterCenterFactoryImpl();
        factory.start(10086);
    }
}