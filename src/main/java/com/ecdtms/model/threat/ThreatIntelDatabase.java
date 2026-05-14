package com.ecdtms.model.threat;

import java.util.ArrayList;
import java.util.List;

public class ThreatIntelDatabase {

    private List<Threat> knownThreats;

    public ThreatIntelDatabase() {
        this.knownThreats = new ArrayList<>();
    }

    public void addThreat(Threat threat) {
        knownThreats.add(threat);
    }

    public Threat searchThreatById(String id) {
        for (Threat t : knownThreats) {
            if (t.getThreatId() != null &&
                t.getThreatId().equalsIgnoreCase(id)) {
                return t;
            }
        }
        return null;
    }

    public void displayThreatIntelligence() {
        System.out.println("=== Threat Intelligence Database ===");

        for (Threat t : knownThreats) {
            System.out.println(t.getThreatId() + " - " + t.getSeverity());
        }
    }
}