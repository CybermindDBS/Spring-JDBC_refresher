package com.example.springbootjdbc.demo;

import java.util.ArrayList;
import java.util.List;

public class SomeClass2 {

    String sVal2;
    List<String> arrayList = new ArrayList<>();

    public String getsVal2() {
        return sVal2;
    }

    public void setsVal2(String sVal2) {
        this.sVal2 = sVal2;
    }

    public List<String> getArrayList() {
        return arrayList;
    }

    public void setArrayList(List<String> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public String toString() {
        return "SomeClass2{" +
                "sVal2='" + sVal2 + '\'' +
                ", arrayList=" + arrayList +
                '}';
    }
}
