package info.wufc.learning.design_pattern.factory.abstractfactory.combineSingleton;

/**
 * @ClassName: ConcreteCreator
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/19/019 16:39
 */
class ConcreteCreator2 extends AbstracctCreator {
    private ConcreteCreator2() {
    }

    private static final ConcreteCreator2 insatnce = new ConcreteCreator2();

    public static ConcreteCreator2 getInstance() {
        return insatnce;
    }

    @Override
    ProductA createProductA() {
        return new ConcreteProductA2();
    }

    @Override
    ProductB createProductB() {
        return new ConcreteProductB2();
    }
}
