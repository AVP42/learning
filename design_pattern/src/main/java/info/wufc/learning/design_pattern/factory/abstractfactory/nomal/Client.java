package info.wufc.learning.design_pattern.factory.abstractfactory.nomal;

/**
 * @ClassName: Client
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/19/019 16:48
 */
public class Client {
    public static void main(String[] args) {
        ConcreteCreator concreteCreator = new ConcreteCreator();
        ProductA productA = concreteCreator.createProductA();
        ProductB productB = concreteCreator.createProductB();
    }

}
