package com.avp42.datastructure._7_quick_sort.selection;

public class SelectMajority {

    /**
     * 选取众数，超过一半
     */
    public int selectMajority(int[] arr){
        int maj = -1;
        for(int i = 0, cnt = 0;i < arr.length; i ++){
            if(cnt == 0){
                maj= arr[i];
                cnt = 1;
            }else{
                cnt = maj == arr[i] ? cnt + 1: cnt - 1;
            }
        }
        return maj;
    }

    /**
     * 选取众数，至少一半
     */
    public int selectMajority2(int[] arr){
       return -1;
    }
}
