package org.teju;

public class Laptop implements Computer {

    public Laptop(){
        System.out.println("laptop Object Created");
    }

    @Override
    public void compile(){
        System.out.println("Compiling using laptop");
    }
}
