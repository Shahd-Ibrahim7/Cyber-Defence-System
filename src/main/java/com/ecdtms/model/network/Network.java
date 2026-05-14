package com.ecdtms.model.network;

import java.util.ArrayList;
import java.util.List;

public class Network {

    private String networkName;
    private List<Device> devices;

    public Network(String networkName) {
        this.networkName = networkName;
        this.devices = new ArrayList<>();
    }

    public void addDevice(Device device) {
        devices.add(device);
        System.out.println("Device " + device.getDeviceId()
                + " added to network: " + networkName);
    }

    public void transmit(Device from, Device to, String data) {

        if (from.getStatus() != DeviceStatus.ACTIVE ||
            to.getStatus() != DeviceStatus.ACTIVE) {
            System.out.println("Transmission blocked: inactive device");
            return;
        }

        System.out.println("Network transmitting data...");

    
       for (Device d : devices) {            
        if (d instanceof Firewall) {       
        Firewall firewall = (Firewall) d;
        firewall.logActivity();
    }
}

        from.sendData(data);
        to.receiveData(data);
    }

    public void scanNetwork() {
        System.out.println("Scanning entire network: " + networkName);

        for (Device d : devices) {
            d.scanVulnerabilities();
        }
    }

    public void displayAllDevices() {
        System.out.println("=== Network: " + networkName + " ===");

        for (Device d : devices) {
            System.out.println(d);
        }
    }

    public List<Device> getDevices() {
        return devices;
    }
}