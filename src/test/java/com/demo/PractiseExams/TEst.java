package com.demo.PractiseExams;

public class TEst {

    public static void main(String[] args) {
        String a="string";
        String b= new String("string");
        String c=a;

        System.out.println(a==b);
        System.out.println(a==c);
        System.out.println(b.equals(c));
    }
}
