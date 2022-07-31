package com.avp42.interview.practise.huawei;

import java.util.Arrays;
import java.util.Scanner;
public class HJ69 {
    public static void main(String[] args) {
        Scanner in= new Scanner(System.in);
        while(in.hasNext()){
            int x = in.nextInt(), y = in.nextInt(), z = in.nextInt();
            int[][] m1 = new int[x][y];
            int[][] m2 = new int[y][z];
            for(int i = 0; i < x; i ++){
                for(int j = 0; j < y; j ++){
                    m1[i][j] = in.nextInt();
                }
            }
            for(int j = 0; j < y; j ++){
                for(int k = 0; k < z; k ++){
                    m2[j][k] = in.nextInt();
                }
            }
            int[][] ret = multiply(m1, m2);
            for(int i = 0; i < x; i ++){
                System.out.println(Arrays.toString(ret[i]));
            }

        }
    }

    private static int[][] multiply(int[][] m1, int[][] m2) {
        int x = m1.length, y= m1[0].length, z = m2[0].length;
        int[][] ret = new int[x][z];
        for(int i = 0; i < x; i++){
            for(int k = 0; k < z; k ++){
                for(int j =0; j < y; j ++){
                    ret[i][k] += m1[i][j] * m2[j][k];
                }
            }
        }
        return ret;
    }

}
