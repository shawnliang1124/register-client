package com.github.shawnliang.register.client;

import java.util.HashMap;
import java.util.Map;

/**
 * Description :   .
 *
 * @author : Phoebe
 * @date : Created in 2021/7/1
 */
public class HttpSender {

    /**
     * 注册服务实例
     * @param registerReq
     * @return
     */
    public RegisterResp register(RegisterReq registerReq) {
        System.out.println("服务实例【" + registerReq + "】，发送请求进行注册......");

        RegisterResp registerResp = new RegisterResp();
        registerResp.setStatus(RegisterResp.SUCCESS);
        return registerResp;
    }


    public HeartBeatResp sendHeartBeat(HeartBeatReq heartBeatReq) {
        System.out.println("服务实例【" + heartBeatReq + "】，发送心跳请求......");

        HeartBeatResp heartBeatResp = new HeartBeatResp();
        heartBeatResp.setStatus(HeartBeatResp.SUCCESS);

        return heartBeatResp;
    }

    /**
     * 从注册中心去拉取注册表，
     * 缓存到本地
     * @return
     */
    public Map<String, Map<String, ServiceInstance>> fetchRegistry() {
        Map<String, Map<String, ServiceInstance>> registry
                = new HashMap<String, Map<String, ServiceInstance>>();

        Map<String, ServiceInstance> serviceInstanceMap
                = new HashMap<String, ServiceInstance>();

        ServiceInstance serviceInstance = new ServiceInstance();
        serviceInstance.setHostname("inventory");
        serviceInstance.setIp("192.168.0.1");
        serviceInstance.setPort(9000);
        serviceInstance.setServiceInstanceId("INVENTORY-SERVICE-192.168.0.1");
        serviceInstanceMap.put(serviceInstance.getServiceInstanceId(), serviceInstance);

        registry.put("INVENTORY-SERVICE", serviceInstanceMap);

        System.out.println("拉取注册表" + registry.toString());
        return registry;
    }

    /**
     * 调用server端的服务下线接口
     * 从 server端下线
     */
    public void cancelFromServer(String serviceName, String instanceName) {
        // TODO 调用server端的服务下线接口， 进行服务的下线
    }
}
