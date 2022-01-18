package info.wufc.learning.design_pattern.flyweight.structure.composite;

import info.wufc.learning.design_pattern.flyweight.structure.single.Flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 具体复合享元角色
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-15 15:13
 */
public class ConcreteCompositeFlyweight extends Flyweight {

    private Map<Character, Flyweight> flies = new HashMap<>();

    @Override
    public void opration(String extrinsicState) {
        flies.entrySet().iterator().forEachRemaining(entry -> entry.getValue().opration(extrinsicState));
    }

    public void add(Character key, Flyweight flyweight) {
        flies.put(key, flyweight);
    }

}
