package com.ecdtms.model.threat;

public class DDoSThreat extends Threat {

    private String targetIp;
    private int packetSize;

    public DDoSThreat(String threatId, String description,
                      SeverityLevel severity,
                      String targetIp, int packetSize) {
        super(threatId, description, severity);
        this.targetIp = targetIp;
        this.packetSize = packetSize;
    }

    @Override
    public void performAttack() {
        System.out.println("DDoS Attack: flooding " + targetIp +
                " with " + packetSize + "KB packets");
    }
}