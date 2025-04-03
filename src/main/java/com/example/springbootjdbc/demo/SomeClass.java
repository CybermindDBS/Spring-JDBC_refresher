package com.example.springbootjdbc.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
//Injects Value from application.properties file
@ConfigurationProperties(prefix = "var")
public class SomeClass {

    int iVal;
    String sVal;
    SomeClass2 someClass2;

    public int getiVal() {
        return iVal;
    }

    public void setiVal(int iVal) {
        this.iVal = iVal;
    }

    public String getsVal() {
        return sVal;
    }

    public void setsVal(String sVal) {
        this.sVal = sVal;
    }

    public SomeClass2 getSomeClass2() {
        return someClass2;
    }

    public void setSomeClass2(SomeClass2 someClass2) {
        this.someClass2 = someClass2;
    }

    @Override
    public String toString() {
        return "SomeClass{" +
                "iVal=" + iVal +
                ", sVal='" + sVal + '\'' +
                ", someClass2=" + someClass2 +
                '}';
    }
}
