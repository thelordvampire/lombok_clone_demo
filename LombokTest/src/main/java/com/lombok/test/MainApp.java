/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lombok.test;

public class MainApp {
    
    public static void main(String[] args) {
        System.out.println("Hello");
        Student stud = new Student();
        System.out.println(stud.getClass());
//        stud.setAge(50);
//        System.out.println(stud.getAge());
    }
    
}
