package com.avp42.interview.practise.huawei;

import java.util.Scanner;
public class HJ51 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int n= in.nextInt();
            ListNode dummy = new ListNode(-1), p = dummy;
            for(int i =0;i < n; i++){
                p.next= new ListNode(in.nextInt());
                p = p.next;
            }
            int k = in.nextInt();
            p = dummy;
            ListNode q = dummy;
            while(k -- > 0){
                p = p.next;
            }
            while(p!=null){
                p = p.next;
                q = q.next;
            }
            System.out.println(q.val);

        }
    }
}

class ListNode{
    int val;
    ListNode next;
    ListNode(int val){
        this.val = val;
    }
}
