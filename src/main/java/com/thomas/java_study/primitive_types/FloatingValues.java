package com.thomas.java_study.primitive_types;

public class FloatingValues {

    public static void main(String[] args){
        float exp = 1.0e-6f;
        System.out.println(exp == 0.000001f);
        if(Float.NaN != Float.NaN){
            System.out.println("NaN is not equal to itself.");
        }
    }
}
