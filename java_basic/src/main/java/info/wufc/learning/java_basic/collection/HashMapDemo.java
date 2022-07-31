package info.wufc.learning.java_basic.collection;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {
    static class Data{
        int a;
        Data(int a){
            this.a = a;
        }
        @Override
        public int hashCode() {
            return a < 10 ? 1 : a;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Data data = (Data) o;
            return a == data.a;
        }
    }

    public static void main(String[] args) {
        Map<Data,Integer> data = new HashMap<>(128);
        for (int i = 10; i < 84; i++) {
            data.put(new Data(i), i);
        }
        // hashMap
        // 添加元素后，treeify需要>8个
        // 移除元素后，红黑树转化为链表则需要<=6个
        for (int i = 0; i < 9; i++) {
            data.put(new Data(i), i);
        }
        System.out.println(data.size());
        data.remove(new Data(0));
        data.remove(new Data(1));
        data.remove(new Data(2));
        System.out.println(data.size());
    }
}
