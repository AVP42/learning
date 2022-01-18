package info.wufc.learning.design_pattern.flyweight.structure.single;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 享元工厂
 *  客户端不可以直接将享元类实例化，而是必须通过一个工厂对象得到。
 *  一般来说，享元工厂对象在整个系统中只有一个，因此可以使用单例模式
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-15 14:36
 */
class FlyweightFactory {
    // 工厂维护所有的享元对象
    // 内蕴状态作为key
    private Map<Character, Flyweight> flies = new HashMap<>();

    private static class Holder{
        static FlyweightFactory instance = new FlyweightFactory();
    }

    public static FlyweightFactory getInstance(){
        return Holder.instance;
    }

    public Flyweight factory(Character intrinsicState){
        if (flies.containsKey(intrinsicState)) {
            return flies.get(intrinsicState);
        }
        ConcreteFlyweight fly = new ConcreteFlyweight(intrinsicState);
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
