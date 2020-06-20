package com.demo.PractiseExams;

import java.util.*;

public class NationWideExample {

    String[] A;

    public  int isUnique(String s) {
        Set<Integer> hashSet = new HashSet<>();
        List<String> newList = new ArrayList();
        for(int i=0;i<A.length;i++) {
            if(A[i].length()!=isUnique(A[i])) {
                continue;
            }
            newList.add(A[i]);


        }
        for (int i=0;i<newList.size();i++) {

            String input = newList.get(i);

            for(int j=i+1;j<newList.size();j++) {
                String concat = input+newList.get(j);

                if(concat.length()!=isUnique(concat)) {
                    continue;
                }
                hashSet.add(concat.length());

            }

        }


        if(null!=hashSet && !hashSet.isEmpty()) {
            return Collections.max(hashSet);
        }
        return 0;

      //  Set<Character> hashSet1 = new HashSet<>();

       /* for(int i=0;i<s.length();i++) {
            hashSet.add(s.charAt(i)); *//*Storing char in hashSet*//*
        }

        return hashSet.size(); *//*return size of string without duplication*//*
*/
    }
}
