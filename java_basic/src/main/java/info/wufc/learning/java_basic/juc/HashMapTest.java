package info.wufc.learning.java_basic.juc;

import java.util.HashMap;

/**
 * @author wufc@viomi.com.cn
 * @since 2022/4/10 0010
 */
public class HashMapTest {

    public static void main(String[] args) {
        // 初始化的时候如果传入的不是2的整数幂，则设置为2的整数幂
        HashMap<String, String> hashmap = new HashMap<>(3);
        hashmap.put("1", "2");
    }
}
