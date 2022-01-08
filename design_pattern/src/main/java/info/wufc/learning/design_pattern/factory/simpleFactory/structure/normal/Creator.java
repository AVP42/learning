package info.wufc.learning.design_pattern.factory.simpleFactory.structure.normal;

/**
 * @ClassName: Factory
 * @Description: 工厂角色
 * @Info: createdBy alien on 2019/10/19/019 10:10
 */
class Creator {

    /**
     * 1.工厂角色提供静态方法，根据传进来的条件进行逻辑判断，生成具体的产品
     * 2.这种模式，并不能避免使用new关键字，只要是通过java编码，肯定是逃避不了语言特性的，
     *      但是使用了工厂角色，new 具体产品()的逻辑从客户端隔离开，客户端不需要知道产品是如何创建的，以及具体的产品实现是什么
     * @param condition
     * @return
     */
    public static Product factory(int condition) {
        if (condition > 1) {
            return new ConcreteProduct();
        }else{
            return new ConcreteProduct2();
        }
    }
}
