package com.example.lib;

public class Practise extends ClassA{
    public static void main(String[] args){
        Practise prc = new Practise();
        prc.cont();
    }
    public void cont(){
        int sum = super.addition(2,3);
        System.out.println("Sum is : " + sum);
    }
}
