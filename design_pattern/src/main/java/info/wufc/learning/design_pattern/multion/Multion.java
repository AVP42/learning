package info.wufc.learning.design_pattern.multion;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Multion
 * @Description: 无上限多例模式
 * @Info: createdBy alien on 2019/10/19/019 20:17
 */
public class Multion {

    private String name;

    /**聚集容器，存放不同“内蕴状态”的对象*/
    private static Map<String, Multion> instances = new HashMap<>();

    // 构造器私有化
    private Multion(String name) {
        this.name = name;
    }

    public static synchronized Multion getInstance(String instanceName) {
        Multion multion = instances.get(instanceName);
        if (multion == null) {
            multion = new Multion(instanceName);
            instances.put(instanceName, multion);
        }
        return multion;
    }
}
