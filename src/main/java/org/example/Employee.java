package org.example;

public class Employee {
    private final String name;
    private final int age;
    private final char gender;
    private final int salary;

    public Employee(String name, int age, char gender, int salary) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public char getGender() {
        return gender;
    }

    public int getSalary() {
        return salary;
    }
}

