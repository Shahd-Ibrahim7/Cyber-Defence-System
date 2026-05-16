package com.ecdtms.model.personnel;

public class Employee extends Person {
    // this class is a child of Person class so it inherate variables and methods of Person class
    protected String department;
    protected double salary;

    public Employee(int id, String name, String email,
        String department, double salary) {
        super(id, name, email);
        this.department = department;
     // salary can't be negative so :
     // Validation anf Error prevention
        if (salary >= 0) {
            this.salary = salary;
        } else {
            this.salary = 0;
        }
    }

    @Override
    public void displayRole() {
        System.out.println("Employee");
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary >= 0) {
            this.salary = salary;
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
        "department='" + department + '\'' +
        ", salary=" + salary +
        "} " + super.toString();
    }
}