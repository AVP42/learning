package info.wufc.learning.design_pattern.flyweight.structure.single;

/**
* 抽象享元对象
* @author fuchang.wu@foxmail.com
* @since 2022-01-15 14:29
*/
public abstract class Flyweight {

     /**
      * 享元对象的商业方法，可以接受客户端传入的外蕴状态
      * @param extrinsicState 外蕴状态
      */
    public abstract void opration(String extrinsicState);
}
