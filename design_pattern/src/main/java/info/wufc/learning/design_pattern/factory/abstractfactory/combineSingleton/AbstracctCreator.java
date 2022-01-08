package info.wufc.learning.design_pattern.factory.abstractfactory.combineSingleton;


/**
 * @ClassName: AbstracctCreator
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/19/019 16:35
 */
abstract class AbstracctCreator {
    static AbstracctCreator getCreator(int condition) {
        if (condition > 1) {
            return ConcreteCreator2.getInstance();
        } else {
            return ConcreteCreator.getInstance();
        }
    }


    abstract ProductA createProductA();
    abstract ProductB createProductB();
}
