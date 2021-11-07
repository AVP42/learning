package com.avp42.basic.bit;

/**
 * @description:
 * @author: fuchang.wu@foxmail.com
 * @date: 2021-08-25 23:09
 */
public class BitOper2 {

    public static void main(String[] args) {
        // (a & b) ^ (a | b) 相当于 a ^ b
        // 左边是交集的，右边是并集，这样在左边为1，右边一定为1，说明两者均为1，结果为0；左边为0且右边为0说明两者均为0，结果为0， 左边为0且右边为1说明两者不同，结果为1.

        System.out.println(2D % 1);
        subset(6);

    }

    public static void subset(int a){
        // 当sub为最后一个子集的时候，(sub-1) & a = a成立了
        for(int sub = (a-1) & a; sub != a; sub = (sub-1) & a){
            System.out.println("sub:" + sub + " a:" + a);
        }
    }


}
