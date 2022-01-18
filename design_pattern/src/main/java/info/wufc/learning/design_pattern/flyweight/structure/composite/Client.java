package info.wufc.learning.design_pattern.flyweight.structure.composite;

import info.wufc.learning.design_pattern.flyweight.structure.single.Flyweight;

/**
 * 客户端
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-15 15:43
 */
public class Client {

    public static void main(String[] args) {
        Flyweight a = FlyWeightFactory.getInstance().factory('a');
        a.opration("first call");
        Flyweight abc = FlyWeightFactory.getInstance().factory("abc");
        abc.opration("composite call");
        FlyWeightFactory.getInstance().checkFlyweight();

    }
}
