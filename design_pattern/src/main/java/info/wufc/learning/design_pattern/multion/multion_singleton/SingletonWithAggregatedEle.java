package info.wufc.learning.design_pattern.multion.multion_singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: SingletonWithAggregatedEle
 * @Description: 单例模式 持有其他类型对象的聚集元素
 * @Info: createdBy alien on 2019/10/19/019 20:31
 */
public class SingletonWithAggregatedEle {

    private static SingletonWithAggregatedEle instance = new SingletonWithAggregatedEle();

    /** 单一实例中拥有聚集对象，这里的对象与单例的类型不一样*/
    private Map<String, Element> elements = new HashMap<>();

    private SingletonWithAggregatedEle() {

    }

    /** 获取单例对象*/
    public static SingletonWithAggregatedEle getInstance() {
        return instance;
    }

    /** 获取聚集元素*/
    public synchronized Element getElement(String name) {
        SingletonWithAggregatedEle instance = getInstance();
        Element element = instance.elements.get(name);
        if (element == null) {
            element = new Element(name);
            instance.elements.put(name, element);
        }
        return element;
    }
}
