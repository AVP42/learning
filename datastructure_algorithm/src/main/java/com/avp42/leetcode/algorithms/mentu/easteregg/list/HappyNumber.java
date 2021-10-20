package com.avp42.leetcode.algorithms.mentu.easteregg.list;

/**
 * @description:
 * @author: wufc@viomi.com.cn
 * @create: 2021-03-07 14:50
 * @since： v 3.1.0
 */
public class HappyNumber {

        public static int getNext(int x){

            int res = 0;
            while(x > 0){
                res +=  (x % 10) * (x %10);
                x = x /10;
            }
            return res;
        }

        public static boolean isHappy(int n) {
            int slow = n, fast = getNext(n);
            while(slow !=fast && fast != 1 && getNext(fast)!=1){
                slow = getNext(slow);
                fast = getNext(getNext(fast));
            }
            return fast ==1 || getNext(fast) ==1;
        }
    public static void main(String[] args) {
        // 分组
        int[] happyNums = new int[]{};
        // 循环
        int sum = 0;
        int n = 1,i=0;
        while(n <= 100000){
           if(isHappy(n)){
               sum += n;
           }
           n ++;
        }
        System.out.println(sum);

        A a = new A();
        int k = a.k;
        int k1 = B.k;

    }

    interface B{
            int k = 10;
    }

    static class A implements B{

    }


}
