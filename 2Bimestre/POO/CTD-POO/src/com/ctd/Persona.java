package com.ctd;

public class Persona {
    private String name;
    private int age;

    public Persona(String name, int age) {
        this.name = name;
        setAge(age);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void birthday(){
        this.age++;
    }
}
