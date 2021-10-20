package com.avp42.basic.bit;

/**
 * @description:
 * @author: fuchang.wu@foxmail.com
 * @date: 2021-08-25 23:09
 */
public class BitOper {

    public static void main(String[] args) {
        // (a & b) ^ (a | b) 相当于 a ^ b
        // 左边是交集的，右边是并集，这样在左边为1，右边一定为1，说明两者均为1，结果为0；左边为0且右边为0说明两者均为0，结果为0， 左边为0且右边为1说明两者不同，结果为1.

        System.out.println(2D % 1);
        System.out.println(doubleToBinaryString(0.2));

        System.out.println(Integer.parseUnsignedInt("10111111111111111111111111111111", 2));
        System.out.println(Integer.toHexString(-3));
        System.out.println(Integer.toBinaryString(-3)); // 11111111111111111111111111111101   java中使用补码表示负数

        System.out.println(Integer.toUnsignedString(1 << 31, 2));
        System.out.println(Integer.parseUnsignedInt("011111111111", 8));
        System.out.println(Integer.toUnsignedString(Integer.parseUnsignedInt("011111111111", 8), 2));
    }


    public static  String doubleToBinaryString(double d){
        return Long.toBinaryString(Double.doubleToLongBits(d));
    }
}
