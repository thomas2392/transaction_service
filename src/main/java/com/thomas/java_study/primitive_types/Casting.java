package com.thomas.java_study.primitive_types;

public class Casting {

    public static void main(String[] args){
        short s = (short) 0x0001; // These bits represent the number 1
        System.out.println(s);
        char c = '\u0031'; // The same bits, as a Unicode character
        System.out.println(c);
        int i1 = s; // Converting the short to an int yields 1
        System.out.println(i1);
        int i2 = c; // Converting the char to an int yields 49
        System.out.println(i2);
        short s2 = Short.MAX_VALUE;
        byte b = (byte)s2;
        System.out.println(b);// There are 258 overflows for the value to reach -1
    }
}
