package com.avp42.datastructure._6_union_set.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @description:
 * @author: wufc@viomi.com.cn
 * @create: 2021-05-01 22:48
 * @since： v 3.1.0
 */
public class Friendship {
        static class UnionSet{
            int[] father;
            UnionSet(int capacity){
                father = new int[capacity+1];
                for(int i = 0;i<=capacity;i++){
                    father[i] = i;
                }
            }

            int find(int ele){
                return father[ele] = father[ele] == ele?ele:find(father[ele]);
            }

            void merge(int a, int b){
                father[find(a)] = find(b);
            }
        }

        public static void main(String[] args) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String[] s = reader.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);
            UnionSet us = new UnionSet(n);
            for(int i = 0; i < m; i++){
                s = reader.readLine().split(" ");
                int a = Integer.parseInt(s[0]);
                int b = Integer.parseInt(s[1]);
                int c = Integer.parseInt(s[2]);
                if(a == 1) us.merge(b,c);
                else if(us.find(b) == us.find(c)) System.out.println("Yes");
                else System.out.println("No");
            }
            reader.close();
        }
}
