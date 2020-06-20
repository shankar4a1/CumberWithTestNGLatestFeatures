package com.demo.PractiseExams;

import java.util.ArrayList;
import java.util.List;

public class Success {

    public static void main(String[] args) {
        String[] strs1 = {"co","dil","ity"};
        String[] strs2 = {"abc","kkk","def","csv"};
        String[] strs3 = {"abc","ade","akl"};
        String[] strs4 = {"eva", "jqw","tym","jan"};
        System.out.println(maxLength(strs1));
        System.out.println(maxLength(strs2));
        System.out.println(maxLength(strs3));
        System.out.println(maxLength(strs4));
    }

    static int res = 0;
    private static int maxLength(String[] strs) {
        res = 0;
        if(hasCommonStrs(strs))
            return 0;
        dp(strs);
        return res;
    }

    static void dp(String[] strs) {
        Status[][] dp = new Status[strs.length][strs.length];
        for(int l=0;l<strs.length;l++) {
            for(int i=0;i + l<strs.length;i++) {
                if(l == 0) {
                    List<String> candidates = new ArrayList<>();
                    candidates.add(strs[i]);
                    dp[i][i] = new Status(strs[i].length(), candidates);
                }else {
                    Status oldStatus = dp[i][i+l-1];
                    int max = oldStatus.val;
                    List<String> newCandidates = new ArrayList<>();
                    for(String candidate : oldStatus.candidates) {
                        if(hasCommon(candidate, strs[i+l]))
                            continue;
                        else {
                            String newCandidate = candidate + strs[i+l];
                            if(newCandidate.length() > max) {
                                newCandidates = new ArrayList<>();
                                newCandidates.add(newCandidate);
                                max = newCandidate.length();
                            }else if(newCandidate.length() == max){
                                newCandidates.add(newCandidate);
                            }
                        }
                    }
                    if(newCandidates.size() == 0)
                        newCandidates = oldStatus.candidates;
                    dp[i][i+l] = new Status(max, newCandidates);
                }
                res = Math.max(res, dp[i][i+l].val);
            }
        }
    }

    static class Status{
        int val;
        List<String> candidates;
        public Status(int val, List<String> candidates) {
            this.val = val;
            this.candidates = candidates;
        }
    }

    static boolean hasCommon(String s1, String s2) {
        int[] cnt = new int[26];
        for(char c : s1.toCharArray()) {
            cnt[c-'a']++;
        }
        for(char c : s2.toCharArray()) {
            cnt[c-'a']++;
        }
        for(int i=0;i<cnt.length;i++) {
            if(cnt[i] > 1)
                return true;
        }
        return false;
    }

    static boolean hasCommonStrs(String[] strs) {
        int[] cnt = new int[26];
        for(String s : strs) {
            for(char c : s.toCharArray()) {
                cnt[c-'a']++;
            }
        }
        for(int i=0;i<cnt.length;i++) {
            if(cnt[i] == strs.length)
                return true;
        }
        return false;
    }
}
