package info.wufc.learning.design_pattern.factory.simpleFactory.structure.normal;

/**
 * @ClassName: Client
 * @Description: 客户角色
 * @Info: createdBy alien on 2019/10/19/019 10:19
 */
class Client {
    public static void main(String[] args) {
        // 客户端虽然不需要知道具体的产品类型，但是需要指定所消费的产品条件，以便工厂进行创建
        Product factory = Creator.factory(1);
        Product factory2 = Creator.factory(2);
    }
}
