package info.wufc.learning.design_pattern.factory.abstractfactory.combinesimple;

/**
 * @ClassName: ConcreteCreator
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/19/019 16:39
 */
class ConcreteCreator extends AbstracctCreator {
    @Override
    ProductA createProductA() {
        return new ConcreteProductA();
    }

    @Override
    ProductB createProductB() {
        return new ConcreteProductB();
    }
}
