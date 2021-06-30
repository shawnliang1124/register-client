package com.github.shawnliang.register.client;

import java.util.UUID;

/**
 * Description :   .
 *
 * @author : Phoebe
 * @date : Created in 2021/7/1
 */
public class RegisterClient {

    public static final String SERVICE_NAME = "inventory-service";
    public static final String IP = "192.168.31.207";
    public static final String HOSTNAME = "inventory01";
    public static final int PORT = 9000;
    private static final Long HEARTBEAT_INTERVAL = 3 * 1000L;

    private String serviceInstanceId;

    private boolean isRunning;

    private HttpSender httpSender;

    private HeartBeatWorker heartBeatWorker;

    public RegisterClient() {
        this.serviceInstanceId = UUID.randomUUID().toString().replace("-", "");
        this.httpSender = new HttpSender();
        this.heartBeatWorker = new HeartBeatWorker();
        this.isRunning = true;
    }

    /**
     * 初始化方法
     * 1、先调用注册线程， 发送注册请求给注册中心
     * 2、调用心跳线程，定时向注册中心发送心跳
     *
     *
     */
    public void init() {
        try {
            // 先开启注册线程，向注册中心发送注册的请求
            RegisterWorker registerWorker = new RegisterWorker();
            registerWorker.start();
            registerWorker.join();

            new HeartBeatWorker().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * interrupt 让休眠的线程从睡眠中恢复
     * 同时修改isRunning的值，让心跳线程从while死循环出跳出
     */
    public void shutDown() {
        heartBeatWorker.interrupt();
        isRunning = false;
    }

    class RegisterWorker extends Thread {

        @Override
        public void run() {
            RegisterReq registerReq = new RegisterReq();

            registerReq.setHostName(HOSTNAME);
            registerReq.setIp(IP);
            registerReq.setServiceName(SERVICE_NAME);
            registerReq.setInstanceId(serviceInstanceId);

            RegisterResp registerResp = httpSender.register(registerReq);

            System.out.println("注册的结果是" + registerResp);
        }
    }

    /**
     * 心跳线程，每隔30S，向注册中心，发送一次请求
     */
    class HeartBeatWorker extends Thread {

        @Override
        public void run() {
            HeartBeatReq heartBeatReq = new HeartBeatReq();

            heartBeatReq.setInstanceId(serviceInstanceId);
            heartBeatReq.setServiceName(SERVICE_NAME);

            while (isRunning) {
                try {
                    HeartBeatResp resp = httpSender.sendHeartBeat(heartBeatReq);
                    System.out.println("续约的结果是 " + resp);
                    Thread.sleep(HEARTBEAT_INTERVAL);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }
    }

}
