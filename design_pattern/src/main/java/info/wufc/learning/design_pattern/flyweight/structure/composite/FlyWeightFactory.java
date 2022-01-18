package info.wufc.learning.design_pattern.flyweight.structure.composite;

import info.wufc.learning.design_pattern.flyweight.structure.single.ConcreteFlyweight;
import info.wufc.learning.design_pattern.flyweight.structure.single.Flyweight;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 享元工厂
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-15 15:21
 */
public class FlyWeightFactory {

    private Map<Character, Flyweight> flies = new HashMap<>();

    private static class Holder{
        static FlyWeightFactory instance = new FlyWeightFactory();
    }

    public static FlyWeightFactory getInstance() {
        return Holder.instance;
    }

    /**
     * 重载的工厂方法，用于提供复合享元对象
     * 由于内蕴状态使用的是Character，所以这里可以使用String
     * 字符串中的每一个字符对应了单纯享元对象的内蕴状态
     * @param compositeState
     * @return
     */
    public Flyweight factory(String compositeState){
        // 复合享元对象不会加入到flies中进行管理，工厂只会管理单纯的享元,复合享元是可以动态的组件出来的。
        ConcreteCompositeFlyweight fly = new ConcreteCompositeFlyweight();
        compositeState.chars().mapToObj(i -> (char) i).forEach(c -> fly.add(c, factory(c)));
        return fly;
    }

    public Flyweight factory(Character intrinsicState){
        if (flies.containsKey(intrinsicState)) {
            return flies.get(intrinsicState);
        }
        Flyweight fly = new ConcreteFlyweight(intrinsicState);
        flies.put(intrinsicState, fly);
        return fly;
    }

    /**
     * 辅助方法
     */
    public void checkFlyweight(){
        Flyweight fly;
        int i = 0;
        for(Iterator<Map.Entry<Character, Flyweight>> it = flies.entrySet().iterator(); it.hasNext(); ){
            Map.Entry<Character, Flyweight> e = it.next();
            System.out.println("Item " + (++i) + ":" + e.getKey());
        }
    }

}
