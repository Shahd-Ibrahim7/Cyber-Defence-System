package com.ecdtms.model.personnel;

import com.ecdtms.model.interfaces.Auditable;

public class SOCManager extends Employee implements Auditable {
    private int teamSize;

    public SOCManager(int id, String name, String email,
        String department, double salary,
        int teamSize) {
        super(id, name, email, department, salary);
        this.teamSize = teamSize;
    }

    @Override
    public void displayRole() {
        System.out.println("SOC Manager");
    }

    @Override
    public void logActivity() {
        System.out.println("SOC Manager activity logged.");
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    @Override
    public String toString() {
        return "SOCManager{" +
        "teamSize=" + teamSize +
        "} " + super.toString();
    }
}