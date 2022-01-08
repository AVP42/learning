package info.wufc.learning.design_pattern.proxy.structure.staticproxy;

/**
 * @ClassName: RealFoo
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/20/020 12:05
 */
class RealFoo implements FooInterface {
    @Override
    public void doWork() {
        System.out.println("RealFoo.doWork");
    }
}
