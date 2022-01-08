package info.wufc.learning.design_pattern.proxy.structure.dynamicproxy.cglib;

/**
 * @ClassName: Cleint
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/20/020 12:23
 */
public class Client  {
    public static void main(String[] args) throws Throwable {
        FooSuper fooSuper = ProxyFoo.create(new RealFoo());
        fooSuper.doWork();
    }
}
