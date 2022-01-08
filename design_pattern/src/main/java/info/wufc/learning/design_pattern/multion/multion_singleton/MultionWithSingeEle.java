package info.wufc.learning.design_pattern.multion.multion_singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MultionWithSingeEle
 * @Description: 持有1个element的多例
 * @Info: createdBy alien on 2019/10/19/019 20:37
 */
public class MultionWithSingeEle {
    /***/
    private static Map<String, MultionWithSingeEle> instances = new HashMap<>();

    private Element element;

    private MultionWithSingeEle(String name) {
        element = new Element(name);
    }

    public static synchronized MultionWithSingeEle getInstance(String name) {
        MultionWithSingeEle multionWithSingeEle = instances.get(name);
        if (multionWithSingeEle == null) {
            multionWithSingeEle = new MultionWithSingeEle(name);
            instances.put(name, multionWithSingeEle);
        }
        return multionWithSingeEle;
    }

    public Element getElement(String name) {
        return getInstance(name).element;
    }
}
