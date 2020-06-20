package com.demo.PractiseExams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CoditlityDemoTest {

    int[] A = {1, 3, 6, 4, 1, 2};

    public int solution(int[] A) {
        int i=1;

        int length=A.length;
        // write your code in Java SE 8

        List<Integer> list = Arrays.stream(A)
                .boxed()
                .collect(Collectors.toList());

        Collections.sort(list);
        for(int j=1;j<length;j++){
            list.contains(j);

        }
      return i;
    }


}


