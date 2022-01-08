package info.wufc.learning.design_pattern.factory.factorymethod.structure.nomal;

/**
 * @ClassName: ConcreteCreator
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/19/019 12:10
 */
class ConcreteCreator implements Creator {

    @Override
    public Product create() {
        return new ConcreteProduct();
    }
}
