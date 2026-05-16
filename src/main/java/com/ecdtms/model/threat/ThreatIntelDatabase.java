package com.ecdtms.model.threat;

import java.util.ArrayList;
import java.util.List;

public class ThreatIntelDatabase {

    private List<Threat> knownThreats; // A collection of known threats, which can include various types of threats such as malware, vulnerabilities, and threat actors.

    public ThreatIntelDatabase() {
        this.knownThreats = new ArrayList<>();
    }

    public void addThreat(Threat threat) {
        knownThreats.add(threat); // Add a new threat to the database
    }

    public Threat searchThreatById(String id) {
        for (Threat t : knownThreats) { 
            if (t.getThreatId() != null && 
                t.getThreatId().equalsIgnoreCase(id)) { 
                return t; // Return the threat if the ID matches (case-insensitive)
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