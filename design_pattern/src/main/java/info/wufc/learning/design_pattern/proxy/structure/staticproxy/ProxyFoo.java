package info.wufc.learning.design_pattern.proxy.structure.staticproxy;

/**
 * @ClassName: ProxyFoo
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/20/020 12:06
 */
class ProxyFoo implements FooInterface {
    /** 持有真实对象*/
    private RealFoo realFoo;

     static ProxyFoo create() {
        return new ProxyFoo();
    }

    @Override
     public void doWork() {
        if (realFoo == null) {
            realFoo = new RealFoo();
        }

        System.out.println("ProxyFoo.doWork begin");
        realFoo.doWork();
        System.out.println("ProxyFoo.doWork end");
    }
}
