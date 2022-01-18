package info.wufc.learning.design_pattern.flyweight.structure.single;

/**
 * 具体享元角色
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-15 14:32
 */
public class ConcreteFlyweight extends Flyweight{

    /**
     * 内蕴状态
     * 应当在被创建的时候赋予，并不会改变
     */
    private  Character intrinsicState = null;

    public ConcreteFlyweight(Character intrinsicState) {
        this.intrinsicState = intrinsicState;
    }




    @Override
    public void opration(String extrinsicState) {
        System.out.println("IntrinsicState = " + intrinsicState + " , extrinsicState = " + extrinsicState);
    }
}
