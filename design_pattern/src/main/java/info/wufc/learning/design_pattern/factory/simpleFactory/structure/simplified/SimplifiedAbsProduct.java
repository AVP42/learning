package info.wufc.learning.design_pattern.factory.simpleFactory.structure.simplified;

/**
 * @ClassName: AbstractProduct
 * @Description: 简化后的简单工厂 抽象产品类作为其子类的工厂角色
 * @Info: createdBy alien on 2019/10/19/019 10:26
 */
class SimplifiedAbsProduct {

    /**
     *
     * @param condition
     * @return
     */
    public static SimplifiedAbsProduct factory(int condition) {
        if (condition > 1) {
            return new ConcreteProduct();
        }else{
            return new ConcreteProduct2();
        }
    }
}
