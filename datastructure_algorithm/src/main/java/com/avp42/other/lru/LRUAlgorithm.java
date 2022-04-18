package com.avp42.other.lru;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUAlgorithm {

    /**
     * 基于LinkedHashMap，要点
     * 1. 无需扩容
     * 2. 实现boolean removeEldestEntry（Map.Entry eldest)方法, 返回true，就会删除eldest
     * 3. 实现并发安全
     *
     */
    static class LruCache extends LinkedHashMap<String, String>{
        private final int size;
        private final Object lock;

        public LruCache(int size) {
            // 避免需要扩容，扩容就是2倍扩容
            super((int)(size * 1.4d), 0.75f, true);
            this.size = size;
            lock = new Object();
        }

        // 复写removeEldestEntry方法
        @Override
        public boolean removeEldestEntry(Map.Entry<String, String> eldest){
            return super.size() > size;
        }

        @Override
        public String get(Object key) {
            synchronized (lock){
                return super.get(key);
            }
        }

        @Override
        public String put(String key, String value) {
            synchronized (lock){
                return super.put(key, value);
            }
        }

        @Override
        public String remove(Object key) {
            synchronized (lock){
                return super.remove(key);
            }
        }

        @Override
        public void clear(){
            super.clear();
        }

    }


}
