package org.teju;

import java.beans.ConstructorProperties;


public class Developer {
    private int age;
    private Computer com;
    public Developer(){
       // System.out.println("Object created");
    }

/*    @ConstructorProperties({"age","lap"})
    public Developer(int age, Computer lap) {
        System.out.println("Parameterized constructor called");
        this.age = age;
    }*/

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
       // System.out.println("setter called");
        this.age = age;
    }

//    public Computer getLap() {
//        return lap;
//    }
//
//    public void setLap(Computer lap) {
//        this.lap = lap;
//    }


    public Computer getCom() {
        return com;
    }

    public void setCom(Computer com) {
        this.com = com;
    }

    public void code()
    {
        System.out.println("coding..");
        com.compile();
       // lap.compile();
    }


}
