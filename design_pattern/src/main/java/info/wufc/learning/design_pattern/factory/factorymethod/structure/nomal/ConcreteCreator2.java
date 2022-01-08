package info.wufc.learning.design_pattern.factory.factorymethod.structure.nomal;

/**
 * @ClassName: ConcreteCreator2
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/19/019 12:13
 */
class ConcreteCreator2 implements Creator {
    @Override
    public Product create() {
        return new ConcreteProduct2();
    }
}
