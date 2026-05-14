package com.ecdtms.model.network;

import com.ecdtms.model.interfaces.Auditable;

public class Firewall extends Device implements Auditable {

    private int rulesCount;
    private String firewallType;

    public Firewall(String deviceId, String ipAddress, DeviceStatus status,
                    
        int rulesCount, String firewallType) {
        super(deviceId, ipAddress, status);
        this.rulesCount = rulesCount;
        this.firewallType = firewallType;
    }

    @Override
    public void scanVulnerabilities() {
        System.out.println("Firewall scanning rules for threats...");
    }

    public boolean filter(String data) {
        if (data.toLowerCase().contains("malware") ||
            data.toLowerCase().contains("attack")) {
            System.out.println("🚨 Firewall blocked malicious traffic!");
            return false;
        }
        return true;
    }

    @Override
    public void logActivity() {
        System.out.println("Firewall Log -> Rules: " + rulesCount +
                " | Type: " + firewallType);
    }

    public int getRulesCount() {
        return rulesCount;
    }
}