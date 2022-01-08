package info.wufc.learning.design_pattern.factory.factorymethod.structure.nomal;

/**
 * @ClassName: Client
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/19/019 12:14
 */
class Client {
    public static void main(String[] args) {

        ConcreteCreator creator = new ConcreteCreator();
        ConcreteCreator2 creator2 = new ConcreteCreator2();

        Product product = creator.create();
        Product product2 = creator2.create();
    }

}
