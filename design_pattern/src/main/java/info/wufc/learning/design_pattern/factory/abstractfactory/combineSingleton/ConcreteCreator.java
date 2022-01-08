package info.wufc.learning.design_pattern.factory.abstractfactory.combineSingleton;

/**
 * @ClassName: ConcreteCreator
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/19/019 16:39
 */
class ConcreteCreator extends AbstracctCreator {
    private ConcreteCreator() {

    }
    /** 必须设置成final，以免被修改*/
    private static final ConcreteCreator instance = new ConcreteCreator();

    public static ConcreteCreator getInstance() {
        return instance;
    }

    @Override
    ProductA createProductA() {
        return new ConcreteProductA();
    }

    @Override
    ProductB createProductB() {
        return new ConcreteProductB();
    }
}
