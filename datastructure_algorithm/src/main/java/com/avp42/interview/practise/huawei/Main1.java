package com.avp42.interview.practise.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main1 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            String text = scanner.nextLine();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 26; i ++){
                char low = (char)('a' + i), up = (char)('A' + i);
                for(int j = 0; j < text.length(); j ++){
                    char c = text.charAt(j);
                    if(c == low || c == up){
                        sb.append(c);
                    }
                }
            }
            for(int i = 0; i < text.length(); i ++){
                char c = text.charAt(i);
                if(!Character.isLetter(c)){
                    sb.insert(i, c);
                }
            }
            System.out.println(sb.toString());
        }
    }




    public static String sort(String text){
        List<Character> letters = new ArrayList<>();
        for(char ch: text.toCharArray()){
            if(Character.isLetter(ch)){
                letters.add(ch);
            }
        }
        // lambda 表达式耗时相当多，没有是35ms，有是160ms，所以算法题上最好不要用lambda
//        letters.sort((x, y) -> Character.toLowerCase(x) - Character.toLowerCase(y));
        letters.sort(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return Character.toLowerCase(o1) - Character.toLowerCase(o2);
            }
        });
        StringBuilder sb = new StringBuilder();
        for(int i =0, j = 0; i < text.length(); i++){
            char c= text.charAt(i);
            if(Character.isLetter(c)){
                sb.append(letters.get(j++));
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main3(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            String text = scanner.nextLine();
            System.out.println(sort(text));
        }
    }


    public static void main2(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text;
        while((text = reader.readLine()) != null) {
            char[] chars  = new char[text.length()];
            int j = 0;
            for(int i = 0; i < text.length(); i ++){
                char c = text.charAt(i);
                if(Character.isAlphabetic(c)){
                    chars[j++] = c;
                }
            }
            char[] arr = new char[j];
            System.arraycopy(chars, 0, arr, 0, j);
            Comparator<Character> cmp = (x, y) -> {
                char c1 = Character.toLowerCase(x);
                char c2 = Character.toLowerCase(y);
                if (c1 == c2) return 0;
                return c1 - c2;
            };
            temp = new char[arr.length];
            mergeSort(arr, 0, arr.length - 1, cmp);
            for(int i = 0, k = 0; i < text.length(); i ++){
                char c = text.charAt(i);
                if(Character.isAlphabetic(c)){
                    chars[i] = arr[k++];
                }else{
                    chars[i] = c;
                }
            }
            System.out.println(new String(chars));
        }
    }

    static char[] temp;
    static void mergeSort(char[] chars, int l, int r, Comparator<Character> cmp){
        if(l >= r) return;
        int mid = ((r - l) >> 1) + l;
        mergeSort(chars, l, mid, cmp);
        mergeSort(chars, mid + 1, r, cmp);
        int x = l, y = mid + 1, k = l;
        while(x <= mid || y <= r){
            if(y > r || x <= mid && cmp.compare(chars[x], chars[y]) <= 0){
                temp[k++] = chars[x ++];
            }else{
                temp[k++] = chars[y ++];
            }
        }
        System.arraycopy(temp, l, chars, l, r - l + 1);
    }

}
