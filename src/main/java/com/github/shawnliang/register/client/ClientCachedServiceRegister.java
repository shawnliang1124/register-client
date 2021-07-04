package com.github.shawnliang.register.client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description :   .
 * 本地注册表缓存
 * @author : Phoebe
 * @date : Created in 2021/7/4
 */
public class ClientCachedServiceRegister {

    private Daemon daemon;

    private RegisterClient registerClient;

    private Map<String, Map<String, ServiceInstance>> cacheRegister
            = new ConcurrentHashMap<String, Map<String, ServiceInstance>>();

    public ClientCachedServiceRegister(RegisterClient registerClient) {
        this.daemon = new Daemon();
        this.registerClient = registerClient;
    }

    /**
     * 初始化
     */
    public void initial() {
        this.daemon.start();
    }

    /**
     * 销毁本地注册表的拉取线程
     */
    public void destroy() {
        daemon.interrupt();
    }

    /**
     * 后台线程定期去拉取注册表
     */
    private class Daemon extends Thread {

        @Override
        public void run() {
            while (registerClient.isRunning()) {
                // 每隔一段时间，去拉取注册表
                try {
                    cacheRegister = registerClient.getHttpSender().fetchRegistry();
                    Thread.sleep(30000L);
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
