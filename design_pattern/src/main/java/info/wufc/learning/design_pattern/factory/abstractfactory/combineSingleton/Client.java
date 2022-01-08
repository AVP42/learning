package info.wufc.learning.design_pattern.factory.abstractfactory.combineSingleton;

/**
 * @ClassName: Client
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/19/019 16:48
 */
public class Client {
    public static void main(String[] args) {
        AbstracctCreator creator = AbstracctCreator.getCreator(1);
        ProductA productA = creator.createProductA();
        ProductB productB = creator.createProductB();
    }
}
