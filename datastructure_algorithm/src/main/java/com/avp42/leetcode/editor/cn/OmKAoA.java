//给定一个字符串 s，请将 s 分割成一些子串，使每个子串都是回文串。 
//
// 返回符合要求的 最少分割次数 。 
//
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：s = "aab"
//输出：1
//解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
// 
//
// 示例 2： 
//
// 
//输入：s = "a"
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：s = "ab"
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 2000 
// s 仅由小写英文字母组成 
// 
// 
// 
//
// 
//
// 注意：本题与主站 132 题相同： https://leetcode-cn.com/problems/palindrome-partitioning-ii
/// 
// Related Topics 字符串 动态规划 
// 👍 20 👎 0


package com.avp42.leetcode.editor.cn;

import java.util.Arrays;

/**
 * 剑指 Offer II 094 最少回文分割
 */
public class OmKAoA{
    public static void main(String[] args) {
        Solution solution = new OmKAoA().new Solution();
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minCut(String s) {
        return minCut2(s);
    }

    public int minCut2(String s) {
        // 二维  区间动态规划,  考察左右边界满足的条件或者不满足的条件
        // dp[i][j] 表示[i,j]区间的最少分割次数
        // dp[i][j] = 0   s[i]==s[j] && dp[i+1][j-1] == 0
        // dp[i][j] = dp[i][k] + dp[k+1][j] + 1
        int n= s.length();
        int[][] dp = new int[n][n];
        for(int j = 0; j < n; j++){
            dp[j][j] = 0;
            for(int i = j - 1; i >= 0; i --){
                dp[i][j] = j - i;
                if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1] == 0){
                    dp[i][j] = 0;
                    continue;
                }
                for(int k = i; k < j; k ++){
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + 1);
                }
            }
        }
//        for (int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }
        return dp[0][n-1];
    }


    public int minCut1(String s) {
        // 做法1：一维
        // dp[i] 表示前i个元素的最少回文个数
        // dp[i] = dp[j] + 1 0<=j<i && isPalindrome(j+1, i)
        int n = s.length();
        int[] dp = new int[n+1];
        for(int i = 1; i <= n; i ++){
            dp[i] = i;
            for(int j = 0; j < i; j ++){
                if(isPalindrome(s, j,i-1)){
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n] - 1;
    }

    boolean isPalindrome(String s, int l, int r){
        while(l < r){
            if(s.charAt(l) != s.charAt(r)) return false;
            l ++;
            r --;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}

