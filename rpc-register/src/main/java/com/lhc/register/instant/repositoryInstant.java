package com.lhc.register.instant;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class repositoryInstant {
    /**
     * 存储服务器名称和服务器地址之间的映射关系
     */
    private static final Map<String, List<InetSocketAddress>> serviceMap = new HashMap<>();

    /**
     * 添加服务
     * @param serverName
     * @param address
     */
    public static void add(String serverName, InetSocketAddress address) {
        if (serviceMap.containsKey(serverName)) {
            serviceMap.get(serverName).add(address);
        }else {
            ArrayList<InetSocketAddress> list = new ArrayList<>();
            list.add(address);
            serviceMap.put(serverName, list);
        }
    }

    /**
     * 获取服务
     * @param serverName
     * @return
     */
    public static List<InetSocketAddress> getServiceAddressList(String serverName) {
        if (!containService(serverName)) return new ArrayList<>();
        return serviceMap.get(serverName);
    }

    /**
     * 判断服务是否存在
     * @param serverName
     * @return
     */
    public static boolean containService(String serverName){
        return serviceMap.containsKey(serverName);

    }


}