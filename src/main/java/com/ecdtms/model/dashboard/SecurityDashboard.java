package com.ecdtms.model.dashboard;

import com.ecdtms.model.incident.Incident;
import com.ecdtms.model.incident.IncidentStatus;
import com.ecdtms.model.network.Device;
import com.ecdtms.model.threat.Threat;

import java.util.List;

public class SecurityDashboard {

    private List<Device> devices;
    private List<Incident> incidents;
    private List<Threat> threats;

    public SecurityDashboard(List<Device> devices,
                              List<Incident> incidents,
                              List<Threat> threats) {
        this.devices = devices;
        this.incidents = incidents;
        this.threats = threats;
    }

    
    public long getOpenIncidentsCount() {
        return incidents.stream()
                .filter(i -> i.getStatus() == IncidentStatus.OPEN)
                .count();
    }

    
    public long getInProgressIncidentsCount() {
        return incidents.stream()
                .filter(i -> i.getStatus() == IncidentStatus.IN_PROGRESS)
                .count();
    }

    
    public long getCompromisedDevicesCount() {
        return devices.stream()
                .filter(d -> d.getStatus().toString().equals("COMPROMISED"))
                .count();
    }

    
    public int getTotalThreats() {
        return threats.size();
    }

    
    public void displaySummary() {
        System.out.println("=== SECURITY DASHBOARD SUMMARY ===");
        System.out.println("Total Devices: " + devices.size());
        System.out.println("Compromised Devices: " + getCompromisedDevicesCount());
        System.out.println("Open Incidents: " + getOpenIncidentsCount());
        System.out.println("In Progress Incidents: " + getInProgressIncidentsCount());
        System.out.println("Total Threats: " + getTotalThreats());
        System.out.println("==================================");
    }
}