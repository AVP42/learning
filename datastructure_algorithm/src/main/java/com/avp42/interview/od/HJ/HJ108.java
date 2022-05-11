package com.avp42.interview.od.HJ;

/**
 * HJ108 求最小公倍数
 *
 * @author wufc@viomi.com.cn
 * @since 2022/5/11 0011
 */
import java.util.*;
public class HJ108 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int a = in.nextInt(), b = in.nextInt();
            // 能同时被a和b整除的最小数为最小公倍数
            // a*b = gcd(a,b) * lcm(a,b)
            System.out.println(helper(a, b));
        }

    }

    private static int helper(int a, int b) {
        return a* b/ gcd(a, b);
    }

    // 辗转相除法
    private static int gcd(int a, int b) {
        return b==0? a : gcd(b, a % b);
    }


}
