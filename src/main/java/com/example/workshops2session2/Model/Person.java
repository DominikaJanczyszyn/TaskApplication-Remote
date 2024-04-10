package com.example.workshops2session2.Model;

import java.io.Serializable;

public class Person implements Serializable {
    private final String name;

    public Person(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean equals(Object obj){
        if(obj == null || obj.getClass()!= getClass()) return false;
        Person p = (Person) obj;
        return p.name.equals(this.name);
    }
    public String toString(){
        return name;
    }
}
