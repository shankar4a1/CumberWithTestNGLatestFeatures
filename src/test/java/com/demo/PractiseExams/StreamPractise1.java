package com.demo.PractiseExams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamPractise1 {

    List<Integer> list=new ArrayList<Integer>();
    List<Integer> listM=new ArrayList<Integer>();
    Map<String,Integer> subjectsScore=new HashMap<String, Integer>();



    public static void main(String[] args) {
        StreamPractise1 streamPractise1=new StreamPractise1();

        streamPractise1.addNumbers();
        streamPractise1.getMultipliersOf5();
        streamPractise1.getHeighestScoreAndSubject();

    }
     public void addNumbers(){pom.xm
        for(int i=1;i<=100;i++){
            list.add(i);
        }


     }
    public void addSubjectsAndScores(){
        subjectsScore.put("Telugu",92);
        subjectsScore.put("English",59);
        subjectsScore.put("Hindi",85);
        subjectsScore.put("Maths",78);
        subjectsScore.put("Science",32);
        subjectsScore.put("Social",52);

    }
     public void getHeighestScoreAndSubject(){
     //  Map<String,Integer> subScore=
         List<Map.Entry> subs=subjectsScore.entrySet().stream().filter(s->s.getKey().equalsIgnoreCase("Telugu")).collect(Collectors.toList());
         System.out.println(subs);
       }

    public void getMultipliersOf5(){
        listM=list.stream().filter(i->i%5==0).collect(Collectors.toList()).stream().filter(i->i%10==0).collect(Collectors.toList()).stream().map(i->i/10).collect(Collectors.toList());
        System.out.println(listM);
    }


}
