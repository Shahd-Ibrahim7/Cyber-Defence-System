package com.ecdtms.model.network;

import com.ecdtms.model.interfaces.Reportable;

public abstract class Device implements Reportable {

    protected String deviceId;
    protected String ipAddress;
    protected DeviceStatus status; // enum to represent device status (ACTIVE, INACTIVE, COMPROMISED)

    public Device(String deviceId, String ipAddress, DeviceStatus status) {
        this.deviceId = deviceId;
        this.ipAddress = ipAddress;
        this.status = status;
    }

    public abstract void scanVulnerabilities(); //Abstract Method 

    public void sendData(String data) {
        if (status != DeviceStatus.ACTIVE) {
            System.out.println("Device " + deviceId + " cannot send data (not active)");
            return;
        }
        System.out.println(deviceId + " sending data: " + data);
    }

    public void receiveData(String data) {
        if (status == DeviceStatus.COMPROMISED) { // Condition to check if device is compromised
            System.out.println("Warning: compromised device received suspicious data!");
        }
        System.out.println(deviceId + " received: " + data);   
    }

    @Override
    public void generateReport() {
        System.out.println("Security Report -> Device: " + deviceId + " | Status: " + status);
    }

    public String getDeviceId() { return deviceId; }
    public String getIpAddress() { return ipAddress; }
    public DeviceStatus getStatus() { return status; }

    public void setStatus(DeviceStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Device{ID=" + deviceId + ", IP=" + ipAddress + ", Status=" + status + "}";
    }
}