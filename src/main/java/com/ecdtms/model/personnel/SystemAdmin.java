package com.ecdtms.model.personnel;

import com.ecdtms.model.interfaces.Auditable;

public class SystemAdmin extends Employee implements Auditable {
    private String accessLevel;

    public SystemAdmin(int id, String name, String email,
                       String department, double salary,
                       String accessLevel) {
        super(id, name, email, department, salary);
        this.accessLevel = accessLevel;
    }

    @Override
    public void displayRole() {
        System.out.println("System Administrator");
    }

    @Override
    public void logActivity() {
        System.out.println("System Admin activity logged.");
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    @Override
    public String toString() {
        return "SystemAdmin{" +
                "accessLevel='" + accessLevel + '\'' +
                "} " + super.toString();
    }
}