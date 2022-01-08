package info.wufc.learning.design_pattern.builder.definition;

/**
 * @ClassName: Client
 * @Description: 客户端
 * @Info: createdBy alien on 2019/12/15/015 21:41
 */
public class Client {
    public static void main(String[] args) {
        ConcreteBuilder2 builder2 = new ConcreteBuilder2();
        IProduct construct = new Director(builder2).construct();
        System.out.println(construct);
    }
}
