package info.wufc.learning.design_pattern.flyweight.structure.single;

/**
 * 客户端
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-15 14:56
 */
public class Client {
    public static void main(String[] args) {
        Flyweight fly = FlyweightFactory.getInstance().factory('a');
        fly.opration("first call");
        fly = FlyweightFactory.getInstance().factory('b');
        fly.opration("second call");
        fly = FlyweightFactory.getInstance().factory('a');
        fly.opration("third call");
        FlyweightFactory.getInstance().checkFlyweight();

    }

}
