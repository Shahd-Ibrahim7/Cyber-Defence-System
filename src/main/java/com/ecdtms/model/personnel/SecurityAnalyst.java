package com.ecdtms.model.personnel;

import com.ecdtms.model.interfaces.Auditable;

public class SecurityAnalyst extends Employee implements Auditable {
    private String specialization;

    public SecurityAnalyst(int id, String name, String email,
                           String department, double salary,
                           String specialization) {
        super(id, name, email, department, salary);
        this.specialization = specialization;
    }

    @Override
    public void displayRole() {
        System.out.println("Security Analyst");
    }

    @Override
    public void logActivity() {
        System.out.println("Security Analyst activity logged.");
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "SecurityAnalyst{" +
                "specialization='" + specialization + '\'' +
                "} " + super.toString();
    }
}