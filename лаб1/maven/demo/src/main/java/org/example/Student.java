package org.example;

public class Student {
    private String name;
    private int age;
    private double grade;
    
    public Student() {}

    public Student(String name, int age, double grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public String getName()        { return name; }
    public void setName(String n)  { this.name = n; }

    public int getAge()            { return age; }
    public void setAge(int a)      { this.age = a; }

    public double getGrade()       { return grade; }
    public void setGrade(double g) { this.grade = g; }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + ", grade=" + grade + "}";
    }
}