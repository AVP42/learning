package info.wufc.learning.design_pattern.proxy.structure.dynamicproxy.cglib;

/**
 * @ClassName: RealFoo
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/20/020 12:16
 */
public class RealFoo extends FooSuper{
    @Override
    public void doWork() {
        System.out.println("RealFoo.doWork");
    }
}
