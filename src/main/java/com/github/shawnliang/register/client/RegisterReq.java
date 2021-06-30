package com.github.shawnliang.register.client;

/**
 * Description :   .
 *
 * @author : Phoebe
 * @date : Created in 2021/7/1
 */
public class RegisterReq {

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 服务请求ip
     */
    private String ip;

    /**
     * 服务请求端口号
     */
    private Integer port;

    /**
     * 机器的名称
     */
    private String hostName;

    /**
     * 服务的实例名称
     */
    private String instanceId;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }
}
