package com.github.shawnliang.register.client;

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
}
