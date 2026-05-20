package com.ecdtms.model.network;

public class Server extends Device {

    private String osVersion;
    private double uptime;

    public Server(String deviceId, String ipAddress, DeviceStatus status, String osVersion) {
        super(deviceId, ipAddress, status);
        this.osVersion = osVersion;
        this.uptime = 100.0; // Initial uptime in hours
    }

    @Override
    public void scanVulnerabilities() {
        System.out.println("Server scanning OS " + osVersion + " for vulnerabilities...");
    }

    public void increaseUptime(double value) {
        uptime += value; // 100.0 + 24.0 = 124.0
        //وقت تشغيل السيرفر
    }

    public String getOsVersion() {
        return osVersion;
    }

    public double getUptime() {
        return uptime;
    }
}