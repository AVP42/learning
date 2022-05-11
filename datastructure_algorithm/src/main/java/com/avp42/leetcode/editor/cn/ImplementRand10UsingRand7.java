//给定方法 rand7 可生成 [1,7] 范围内的均匀随机整数，试写一个方法 rand10 生成 [1,10] 范围内的均匀随机整数。 
//
// 你只能调用 rand7() 且不能调用其他方法。请不要使用系统的 Math.random() 方法。 
//
// 
// 
//
// 每个测试用例将有一个内部参数 n，即你实现的函数 rand10() 在测试时将被调用的次数。请注意，这不是传递给 rand10() 的参数。 
//
// 
//
// 示例 1: 
//
// 
//输入: 1
//输出: [2]
// 
//
// 示例 2: 
//
// 
//输入: 2
//输出: [2,8]
// 
//
// 示例 3: 
//
// 
//输入: 3
//输出: [3,8,10]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= n <= 105 
// 
//
// 
//
// 进阶: 
//
// 
// rand7()调用次数的 期望值 是多少 ? 
// 你能否尽量少调用 rand7() ? 
// 
// Related Topics 数学 拒绝采样 概率与统计 随机化


package com.avp42.leetcode.editor.cn;

/**
 * 470 用 Rand7() 实现 Rand10()
 */
public class ImplementRand10UsingRand7{
//    public static void main(String[] args) {
//        Solution solution = new ImplementRand10UsingRand7().new Solution();
//    }

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */
//class Solution extends SolBase {
//    public int rand10() {
//        // 思路：拒绝采样
//        int a,b,c;
//        while(true){
//            a = rand7();
//            b = rand7();
//            c = (b-1) * 7 + a;
//            if(c <= 40) break;
//            // 得到1到9的随机数，再随机一次rand7，可得到7x9=63(一次确定横坐标，一次确定纵坐标)的随机数，拒绝61到63
//            // 得到1到3的随机数，再随机一次rand7，可得到7x3=21的随机数，拒绝21
//            // 得到1的随机数，再随机一次rand7，也得到1到7的随机数，已经没有意义，这时就可以重新来了
//            b = rand7();
//            c = (c-40-1)*7+b;
//            if(c<=60) break;
//
//            b = rand7();
//            c = (c-60-1)*7 +b;
//            if(c<=20) break;
//        }
//        return 1 + (c-1)%10;
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)

}