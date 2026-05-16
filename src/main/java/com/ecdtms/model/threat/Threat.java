package com.ecdtms.model.threat;

import com.ecdtms.model.interfaces.Reportable;

public abstract class Threat implements Reportable {

    protected String threatId;
    protected String description;
    protected SeverityLevel severity;

    public Threat(String threatId, String description, SeverityLevel severity) {
        this.threatId = threatId;
        this.description = description;
        this.severity = severity;
    }

    public abstract void performAttack(); // كل نوع Threat لازم يحدد بنفسه طريقة الهجوم

    @Override
    public void generateReport() {
        System.out.println("Threat Analysis Report [" + threatId + "] - Severity: " + severity);
    }

    public String getThreatId() {
        return threatId;
    }

    public SeverityLevel getSeverity() {
        return severity;
    }
}