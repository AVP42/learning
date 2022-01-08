package info.wufc.learning.design_pattern.builder.simplify;

/**
 * @ClassName: Client
 * @Description: 客户端
 * @Info: createdBy alien on 2019/12/15/015 21:41
 */
public class Client {
    public static void main(String[] args) {
        ConcreteBuilder1 builder = new ConcreteBuilder1();

        System.out.println(builder.construct());
    }
}
