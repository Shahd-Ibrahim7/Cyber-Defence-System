package com.ecdtms.model.network;

import com.ecdtms.model.personnel.Employee;

public class Workstation extends Device {

    private Employee assignedUser;

    public Workstation(String deviceId, String ipAddress,
                       
        DeviceStatus status, Employee assignedUser) {
        super(deviceId, ipAddress, status);
        this.assignedUser = assignedUser;
    }

    @Override
    public void scanVulnerabilities() {
        System.out.println("Scanning workstation of: " + assignedUser.getName());
    }

    public Employee getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(Employee assignedUser) {
        this.assignedUser = assignedUser;
    }
}