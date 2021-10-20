package com.avp42.datastructure.chapter9_string_match;

import java.util.Arrays;
import java.util.Objects;

/**
 * @description: 字符串匹配暴力解法
 * @author: fuchang.wu@foxmail.com
 * @date: 2021-10-11 20:35
 */
public class String_match {

    public static int brute_force(String text, String pattern){
        int n = text.length(), m = pattern.length();
        // 剩余不足m个元素，就不再继续
        for(int i = 0; i < n - m + 1; i ++){
            boolean flag = true;
            for(int j = 0; j < m; j ++){
                if(text.charAt(i + j) == pattern.charAt(j)) continue;
                flag = false;
                break;
            }
            if(flag) return i;
        }
        return -1;
    }

    /**
     * kmp 算法   参考邓俊辉老师实现
     * 适配位置对应的文本串的i位置，对应模式串的j位置
     */
    public static int kmp_1(String text, String pattern){
        int n = text.length(), m = pattern.length();
        // next表中每一项代表当前位置发生失配时，模式串中下一个应当与i对齐的位置(前面的已经匹配过了)
        // 所以构建next表时，遍历到每个元素时
        int[] next = getNext_1_1(pattern);
//        int[] next = getNext_2(pattern);
        int i = 0, j = 0;
        while(i < n && j < m){
            if(j < 0 || text.charAt(i) == pattern.charAt(j)){
                i ++;
                j ++;
            }else{
                /*当i和j发生失配时*/
                j = next[j];
            }
        }
        // 如果模式串指针j>=m则说明全部匹配成功
        return j == m ? i - m : -1;
    }

    /**
     *  构建next表，每一项的元素是失配情况下，下一个顶上去的元素
     *  本质上对应的是该元素前面的最大真前缀和最大真后缀的长度
     *  所以实际上是模式串的自我匹配：文本串从i=0开始，模式串从j=-1开始（j=-1位置是一个通配符）
     *  例子：文本串aecaeaecaed  模式串 aecaed
     *
    */
    private static int[] getNext_1_1(String pattern){
        int m = pattern.length();
        int[] next = new int[m];
        // 文本串指针 和 模式串指针
        int i = 0,  j = next[0] = -1;
        while(i < m - 1){
            if(j < 0 || pattern.charAt(i) == pattern.charAt(j)){
                // 当前位置匹配，则在下一个位置i+1失配时，可以直接对齐到j+1
                i ++;
                j ++;
                next[i] = j;
            }else{
                // 当前位置失配，则往前找到可以对齐的位置
                j = next[j];
            }
        }
        return next;
    }

    /**
     * 构建next表优化
     * 问题所在：假设在pattern[j]处失配，进行多次跳跃之后j=next[j]对应的对齐元素依然等同于pattern[j]，那么这种跳跃式没有意义的
     * 改进：在构建next表的时候，就进行优化，如果pattern[j] == pattern[next[j]]，则继续往前跳一次。在循环走完之后，对于同一个失配的patter[j]就都会第一次失配对应的next[j]
     * 比如文本串000100001，模式串：00001
     */
    private static int[] getNext_1_2(String pattern){
        int m = pattern.length();
        int[] next = new int[m];
        int i = 0, j = next[0] = -1;
        while(i <m -1){
            if(j < 0 || pattern.charAt(i) == pattern.charAt(j)){
                i ++;
                j ++;
                // 如果模式串跳跃后的对齐元素依然等于之前的元素，则继续跳一次。
                next[i] = pattern.charAt(i) == pattern.charAt(j) ? next[j] : j;
            }else{
                j = next[j];
            }
        }
        return next;
    }


    /**
     *  KMP 算法，船长实现
     *  区别在next数组存放的值有点不一样
     *  next数组存放的是以当前元素结尾(包含当前元素)，真前缀和真后缀相等的长度最大，此时最长真前缀最后一个元素的下标
     *  同时也就是当前元素失配时，下一个应当将该下标对齐到当前元素, 此时该元素一定是匹配的了，也就不需要像上面那样进行getNext_1_2的优化
     */
    public static int kmp_2(String text,  String pattern){
         int n = text.length(), m = pattern.length();
         int[] next = getNext_2_1(pattern);
         for(int i = 0, j = -1; i < n; i ++){
             while(j > -1 && text.charAt(i) != pattern.charAt(j+1)) j = next[j];
             if(text.charAt(i) == pattern.charAt(j+1)) {
                 j++;
             }
             if(j == m - 1) {
                 return i - j;
             }
         }
         return -1;
    }

    private static int[] getNext_2_1(String pattern){
        int m = pattern.length();
        int[] next = new int[m];
        next[0] = -1;
        for(int i = 1, j = -1; i < m; i++){
            while(j > -1 && pattern.charAt(i) != pattern.charAt(j+1)) j = next[j];
            if(pattern.charAt(i) == pattern.charAt(j+1)) j ++;
            next[i] = j;
        }
        return next;
    }


    /**
     * sunday 算法
     * 引入：如果我们在i位置对齐，且在i+j位置失配了，我们需要将模式串往后移动，则i+m位置的元素必然要在模式串中，
     * 因此我们在模式串中从后往前找到第一个与text[i+m]相等的元素位置，然后将其与文本串的i+m位置对齐即可。
     * 核心在于预处理好黄金分割点
     */
    public static int sunday(String text, String pattern){
        int n = text.length(), m = pattern.length();
        int[] pos = new int[128];
        // 如果不存在，则整串移动
        Arrays.fill(pos, -1);
        // 记录每个元素最后出现的位置
        for(int i = 0 ; i < m; i ++) pos[pattern.charAt(i)] = i;
        // i + m <= n 如果剩余长度不足，则没必要进行匹配
        // m - pos[...] 表示模式串往后移动多少位，则i跟着也移动多少位
        for(int i = 0; i + m <= n; ){
            boolean flag = true;
            // 为了两两对齐比对，我们使用i+j代表文本串的指针
            for(int j = 0; j < m; j ++){
                if(text.charAt(i+j) != pattern.charAt(j)){
                    flag = false;
                    break;
                }
            }
            if(flag) return i;
            // 判断text.charAt(i+m) 合法，也就是末尾都对齐，依然失配的情况
            if(i + m < n){
                i += (m - pos[text.charAt(i + m)]);
            }else{
                i += 1;
            }
        }
        return -1;
    }


    /**
     *  shift-and 算法
     *  思想：
     *      1.记录模式串每个元素的每次出现的位置，而不仅仅是最后一次出现的位置，使用二进制来存储这个信息。
     *      2.遍历文本串，维护一个P变量，也是一个二进制，第j位(从低到高，j从1开始)为1时，表示以当前元素结尾，后j个元素与模式串的前j个元素匹配。
     *      3.遍历到文本串后面的元素时，根据该元素出现的位置，和前面已知的P，推到出当前元素对应的P。具体地
     *          3.1 假设文本串I位置元素对应的变量P的J位为1(从低到高，i从1开始)，那么如果文本串中I+1位元素在模式串J+1位置处出现，说明后j+1个元与模式串的前j+1元素匹配，那么P的的J+1位也就等于1
     *          3.2 假设文本串I位置元素对应的变量P均为0，那么如果文本串中I+1位元素在模式串第1个位置出现，那么以I+1元素结尾，只有后1个元素与前1个元素匹配
     *      4.遍历过程中，如果发现P的第m位(m是模式串长度)是1，则说明全部匹配了。
     *  应用场景：
     *      1.根据模式串预处理了信息，在遍历文本串过程中仅仅需要维护变量P，类似自动机的思想，每来一个字符，我们就可以更新P变量 ==> 流数据的匹配处理
     *      2.预处理过程中，我们保存了模式串每个元素出现的位置，这样，根据上面的过程，我们可以处理同一个位置匹配多个字符的情况。
     *
     *  特殊地：如果模式串比较长，int只有4个字节，32位，那么可以使用bitmap 二进制数组
     *
     */
    public static int shift_and(String text, String pattern){
        int n = text.length(), m = pattern.length();
        int[] code = new int[128];
        for(int i = 0; i < m; i ++) code[pattern.charAt(i)] |= (1 << i);
        int p = 0;
        // shift-and 算法i 是最后对比的元素
        for(int i = 0; i < n; i++){
            // (p << 1) & code[...] 相当于3.1情况
            p = (p << 1 | 1) & code[text.charAt(i)];
            // !!!! 切记，与操作之后只有1位是1，不代表二进制表示的数就是1
            if((p & (1 << (m-1))) > 0) return i - m + 1;
        }
        return -1;
    }



    public static void main(String[] args) {
        String text = "hello";
        String pattern1 = "he";
        String pattern2 = "low";
        String pattern3 = "lo";
        String pattern4 = "wh";
        String pattern5 = "elw";

        System.out.println("暴力");
        System.out.println(Objects.equals(brute_force(text, pattern1), 0));
        System.out.println(Objects.equals(brute_force(text, pattern2), -1));
        System.out.println(Objects.equals(brute_force(text, pattern3), 3));
        System.out.println(Objects.equals(brute_force(text, pattern4), -1));
        System.out.println(Objects.equals(brute_force(text, pattern5), -1));

        System.out.println("kmp 1");
        System.out.println(Objects.equals(kmp_1(text, pattern1), 0));
        System.out.println(Objects.equals(kmp_1(text, pattern2), -1));
        System.out.println(Objects.equals(kmp_1(text, pattern3), 3));
        System.out.println(Objects.equals(kmp_1(text, pattern4), -1));
        System.out.println(Objects.equals(kmp_1(text, pattern5), -1));

        System.out.println("kmp 2");
        System.out.println(Objects.equals(kmp_2(text, pattern1), 0));
        System.out.println(Objects.equals(kmp_2(text, pattern2), -1));
        System.out.println(Objects.equals(kmp_2(text, pattern3), 3));
        System.out.println(Objects.equals(kmp_2(text, pattern4), -1));
        System.out.println(Objects.equals(kmp_2(text, pattern5), -1));

        System.out.println("sunday");
        System.out.println(Objects.equals(sunday(text, pattern1), 0));
        System.out.println(Objects.equals(sunday(text, pattern2), -1));
        System.out.println(Objects.equals(sunday(text, pattern3), 3));
        System.out.println(Objects.equals(sunday(text, pattern4), -1));
        System.out.println(Objects.equals(sunday(text, pattern5), -1));

        System.out.println("shit-and");
        System.out.println(Objects.equals(shift_and(text, pattern1), 0));
        System.out.println(Objects.equals(shift_and(text, pattern2), -1));
        System.out.println(Objects.equals(shift_and(text, pattern3), 3));
        System.out.println(Objects.equals(shift_and(text, pattern4), -1));
        System.out.println(Objects.equals(shift_and(text, pattern5), -1));
//
//        System.out.println("---------");
//        System.out.println(Arrays.toString(getNext_1("llo")));
        System.out.println(Arrays.toString(getNext_1_2("llo")));
        System.out.println(Arrays.toString(getNext_1_2("aecaed")));

    }
}
