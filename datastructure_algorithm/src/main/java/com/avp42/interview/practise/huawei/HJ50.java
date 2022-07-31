package com.avp42.interview.practise.huawei;

/**
 * 四则运算
 * 负数的处理
 * 括号的处理
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
public class HJ50 {
    static Map<Character, Character> brackets = new HashMap<>(8);
    static Map<Character, Integer> priorities = new HashMap<>(8);
    static {
        brackets.put(')', '(');
        brackets.put(']', '[');
        brackets.put('}', '{');

        priorities.put('+', 1);
        priorities.put('-', 1);
        priorities.put('*', 2);
        priorities.put('/', 2);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String exp;
        while(in.hasNextLine()){
            exp = in.nextLine();
            int ret = calculate(exp);
            System.out.println(ret);
        }
    }

    // 究极双栈
    private static int calculate(String exp) {
        Stack<Character> ops = new Stack<>();
        Stack<Integer> nums = new Stack<>();

        // 1.预处理
        // 1.1 防止第一个数是负数
        nums.push(0);
        // 1.2 处理空格
        exp = exp.replace(" ", "");
        char[] chars = exp.toCharArray();

        int num = 0;
        for (int i = 0; i < exp.length(); i++) {
            char c = chars[i];
            if (Character.isDigit(c)) {
                // 2. j从i开始
                int j = i;
                while (j < exp.length()) {
                    if (Character.isDigit(chars[j])) {
                        // 使用括号，避免溢出
                        num = num * 10 + (chars[j] - '0');
                    } else {
                        break;
                    }
                    j ++;
                }
                i = j - 1;
                nums.push(num);
                num = 0;
                continue;
            }
            if (brackets.containsValue(c)) {
                ops.push(c);
            } else if (brackets.containsKey(c)) {
                char target = brackets.get(c);
                while (ops.peek() != target) {
                    cal(ops, nums);
                }
                ops.pop();
            } else {
                // 3. 处理(-4/2)这种情况
                if (i > 0 && (brackets.containsValue(chars[i - 1]) || chars[i - 1] == '+' || chars[i - 1] == '-')) {
                    nums.push(0);
                }
                // 4. 还要判断栈顶是否是'('
                while (!ops.isEmpty() && !brackets.containsValue(ops.peek()) && priorities.get(ops.peek()) >= priorities.get(c)) {
                    cal(ops, nums);
                }
                ops.push(c);
            }
        }
        // 最后进行计算一次
        while(!ops.isEmpty()){
            cal(ops, nums);
        }
        return nums.peek();
    }

    static void cal(Stack<Character> ops, Stack<Integer> nums){
        if(nums.isEmpty() || nums.size() < 2) return;
        if(ops.isEmpty()) return;
        int b = nums.pop();
        int a = nums.pop();
        char op = ops.pop();
        int ret;
        if(op == '+'){
            ret =  a + b;
        }else if(op == '-') {
            ret =  a - b;
        }else if(op == '*'){
            ret =  a * b;
        }else {
            ret =  a / b;
        }
        nums.push(ret);
    }



// 未通过  https://labuladong.github.io/algo/4/31/129/
//    private static int calculate(String exp){
//        exp += '#';
//        Stack<Integer> nums = new Stack<>();
//        int num = 0; char sign = '+';
//        for(int i = 0; i < exp.length(); i ++){
//            char c = exp.charAt(i);
//            if(Character.isDigit(c)){
//                // 避免溢出
//                num = num * 10 + (c - '0');
//            }else if(brackets.containsKey(c)){
//                int r = findRightBracket(brackets.get(c), i+1, exp);
//                num =  calculate(exp.substring(i + 1, r + 1));
//                System.out.println(exp.substring(i + 1, r + 1));
//                System.out.println(num);
//                i = r;
//            } else if(c != ' ' || i == exp.length() - 1){
//                switch(sign){
//                    case '+':
////                        System.out.println("+" + num);
//                        nums.push(num);
//                        break;
//                    case '-':
////                        System.out.println("-" + num);
//                        nums.push(-num);
//                        break;
//                    case '*':
////                        System.out.println("c:" + c + " "+ nums.peek() + " * " + num);
//                        nums.push(nums.pop() * num);
//                        break;
//                    case '/':
////                        System.out.println("c:" + c + " "+ nums.peek() + " / " + num);
//                        nums.push(nums.pop() / num);
//                        break;
//                    default:
//                }
//                sign = c;
//                num = 0;
//                if(brackets.containsValue(c)){
//                    break;
//                }
//            }
//        }
//        return sum(nums);
//    }
//
//    private static int findRightBracket(char target, int i, String exp) {
//        while(i < exp.length() && exp.charAt(i) != target) i ++;
//        return i;
//    }
//
//    // 累加栈上的元素
//    private static int sum(Stack<Integer> nums) {
////        System.out.println("size:" + nums.size());
//        int ret = 0;
//        while(!nums.isEmpty()) ret += nums.pop();
//        return ret;
//    }

}
