package com.avp42.interview.practise.huawei;

import java.util.Scanner;
public class HJ83 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int n = in.nextInt(), m = in.nextInt();
            int x1 = in.nextInt(), y1 = in.nextInt(), x2 = in.nextInt(), y2 = in.nextInt();
            int row = in.nextInt(), column = in.nextInt();
            int x = in.nextInt(), y = in.nextInt();
            if(n <= 9 && m <= 9) {
                System.out.println(0);
            }else{
                System.out.println(-1);
            }
            if(x1 >= n || x2 >= n || y1 >= m || y2 >= m){
                System.out.println(-1);
            }else{
                System.out.println(0);
            }
            if(n < 9){
                System.out.println(0);
                n += 1;
            }else{
                System.out.println(-1);
            }
            if(m < 9){
                m += 1;
                System.out.println(0);
            }else{
                System.out.println(-1);
            }
            if(x>= n || y2 >= m){
                System.out.println(-1);
            }else{
                System.out.println(0);
            }
        }
    }
}
