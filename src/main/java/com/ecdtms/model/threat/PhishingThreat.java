package com.ecdtms.model.threat;

public class PhishingThreat extends Threat {

    private String targetEmail;
    private String fakeUrl;

    public PhishingThreat(String threatId, String description,
        SeverityLevel severity,
        String targetEmail, String fakeUrl) {
        super(threatId, description, severity);
        this.targetEmail = targetEmail;
        this.fakeUrl = fakeUrl;
    }

    @Override
    public void performAttack() {
        System.out.println("Phishing attack: sending " + fakeUrl + " to " + targetEmail);
    } // example: Phishing attack: sending http://fakebank.com to john.doe@example.com 
}