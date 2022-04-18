package com.avp42.datastructure.bitmap;

import com.googlecode.javaewah.EWAHCompressedBitmap;

import java.util.BitSet;

/**
 * https://www.cnblogs.com/programmer-xiaohui/p/10480653.html   redis bitmap
 *
 * bitmap 位图算法
 * 使用二进制来存储，用下标来表示元素i，该位为0，表示一种状态(比如不存在），为1表示另一种状态(比如存在)
 *
 * 作用：
 *    极小的存储空间，并且利用位运算可以快速完成查询，去重，统计，交集，并集
 *
 * 注意：在某些场景下，直接非运算得到的运算是不正确的。比如对用户90后的标签使用一个bitmap，对用户00后使用一个bitmap，
 * 这样如果要计算非90后的用户，直接对90后的bitmap进行非运算得到的结果必然是不正确的，这里的问题是为0的位，可能是其他00后用户，也可能是不存在的用户
 * 为此，我们可以再维护一个全量的bigmap，如此，我们通过异或操作，就可以得到非90用户了
 *
 * bitmap空间优化，避免只有少数的用户，缺使用了较多位的bitmap
 *
 */
public class BitMap {

    /**jdk中的BitSet是一种较为简单的实现
     * bitset 实现了一个可增长的二进制数组
     * 每位对应的值是一个boolean
     * bitset 打包成一个long数组，数组每个元素称为“word”，64位的word包含6位的掩码以及58位用于存储
     * https://www.runoob.com/java/java-bitset-class.html
     */
    public static void jdk_bitSet(){
        BitSet bs = new BitSet();
    }


    public static void EWAHCompressedBitMap(){
        EWAHCompressedBitmap bitmap = new EWAHCompressedBitmap();
    }


    public static void mine_implementation(){
        
    }

}
