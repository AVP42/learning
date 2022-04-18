package com.avp42.datastructure.chapter9_string_match.exercise;

import java.util.Arrays;

/**
 * @author wufc@viomi.com.cn
 * @since 2022/4/9 0009
 */
public class StringMatch {

    public static void main(String[] args) {
        StringMatch obj = new StringMatch();
        String text = "hellaollo";
        System.out.println(obj.kmp(text, "llo"));
        System.out.println(obj.kmp(text, "llow"));
        System.out.println(obj.sunday(text, "llo"));
        System.out.println(obj.sunday(text, "lalow"));
    }

    public  int kmp(String text, String pattern){
        int n = text.length(), m = pattern.length();
        int[] next = getNext(pattern);
        for(int i = 0, j = -1; i < n; i++){
            while(j != -1 && text.charAt(i) != pattern.charAt(j+1)) j = next[j];
            if(text.charAt(i) == pattern.charAt(j + 1)) j ++;
            if(j  == m - 1) return i - j;
        }
        return -1;
    }

    private int[] getNext(String pattern){
        int m= pattern.length();
        int[] next = new int[m];
        next[0] = -1;
        for(int i = 1, j = -1; i< m;i ++){
            while(j != -1 && pattern.charAt(i) != pattern.charAt(j+1)) j = next[j];
            if(pattern.charAt(i ) == pattern.charAt(j+1)) j ++;
            next[i] = j;
        }
        return next;
    }

    public int sunday(String text, String pattern){
        int n = text.length(), m = pattern.length();
        int[] lastOccur = new int[128];
        Arrays.fill(lastOccur, -1);
        for(int i = 0; i < m; i++){
            lastOccur[pattern.charAt(i)] = i;
        }
        for(int i = 0; i + m <= n; ){
            boolean flag = true;
            for(int j = 0; j < m; j ++){
                if(text.charAt(i + j) != pattern.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if(flag) return i;
            if(i + m == n){
                i += 1;
            }else{
                i += m - lastOccur[text.charAt(i + m)];
            }
        }
        return -1;
    }

    public int shift_And(String text, String pattern){
        int n = text.length(), m = pattern.length();
        int[] d = new int[128];
        for(int i =0 ; i < m; i ++){
            d[pattern.charAt(i)] |= (1 << i);
        }
        int p = 0;
        for(int i = 0; i<n; i++){
            p = ((p << 1) | 1) & d[text.charAt(i)];
            if((p & (1 << (m - 1))) > 0) return i - m + 1;
        }
        return -1;


    }




}
