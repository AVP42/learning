package info.wufc.learning.design_pattern.factory.abstractfactory.combinesimple;

/**
 * @ClassName: AbstracctCreator
 * @Description: 抽象工厂
 * @Info: createdBy alien on 2019/10/19/019 16:35
 */
abstract class AbstracctCreator {
    static AbstracctCreator getCreator(int condition) {
        if (condition > 1) {
            return new ConcreteCreator2();
        } else {
            return new ConcreteCreator();
        }
    }

    abstract ProductA createProductA();
    abstract ProductB createProductB();
}
