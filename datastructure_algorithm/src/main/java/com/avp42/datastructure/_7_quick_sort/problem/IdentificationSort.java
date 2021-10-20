package com.avp42.datastructure._7_quick_sort.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;

/**
 * @description: onlinejudge of kkb #103
 * @author: wufc@viomi.com.cn
 * @create: 2021-06-06 15:29
 * @since： v 3.1.0
 */
public class IdentificationSort {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String[] ids = new String[n];
        String line;
        int ind = 0;
        while((line = reader.readLine()) != null && line.length() > 0){
            ids[ind ++] = line;
        }
        // 进行快速排序
        Comparator<String> cmp = (x, y) -> {
            int birthdayCmpRes = y.substring(6,14).compareTo(x.substring(6,14));
            if(birthdayCmpRes != 0){
                return birthdayCmpRes;
            }
            return x.compareTo(y);
        };
        quickSort(ids, cmp, 0, n -1);
        // 输出
        for(String id : ids){
            System.out.println(id);
        }

    }

    public static void quickSort(String[] ids, Comparator<String> cmp, int l, int r){
        if(l >= r) return;
        while(l < r){
            int p1 = l, p2 = r;
            String base = getMid(ids, cmp, l, r);
            do{
                while(cmp.compare(ids[p2], base) > 0) p2 --;
                while(cmp.compare(ids[p1], base) < 0) p1 ++;
                if(p1 <= p2){
                    swap(ids, p1, p2);
                    p1 ++; p2 --;
                }
            }while(p1 <= p2);
            quickSort(ids, cmp, p1, r);
            r = p2;
        }
    }

    public static String getMid(String[] ids, Comparator<String> cmp, int l , int r){
        int mid = (l + r) >> 1;
        String a = ids[l], b = ids[r] , c = ids[mid], temp;
        if(cmp.compare(a,b)>0) {temp = a; a =b ; b = temp;}
        if(cmp.compare(a,c) >0) {temp = a; a = c ; c = temp;}
        if(cmp.compare(b, c) >0) {temp = b; b = c; c =  temp;}
        return b;
    }

    public static void swap(String[] ids, int a, int b){
        String  temp = ids[a];
        ids[a] = ids[b];
        ids[b] = temp;
    }

}
