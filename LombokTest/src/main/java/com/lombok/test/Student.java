/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lombok.test;

import com.bao.getter.annotation.MyGetter;
import com.bao.getter.annotation.MySetter;


/**
 *
 * @author bao
 */
public class Student {
    
    @MySetter
    @MyGetter
    private String name;
    
    private int id;
    
    @MySetter
    @MyGetter
    private int age;
    
   
    public int getId() {
        return this.id;
    }
    
    public int getId1() {
        return this.id;
    }

    @Override
    public String toString() {
        return "student: name= "+name+", ";
    }
    

}

