package info.wufc.learning.design_pattern.builder.definition;

/**
 * @ClassName: ConcreteBuilder
 * @Description: 具体建造者
 * @Info: createdBy alien on 2019/12/15/015 21:40
 */
public class ConcreteBuilder2 extends AbstractBuilder{
    IProduct product2 = new Product2();

    @Override
    void buildPart1() {
        product2.setPart2("part2");
        System.out.println("ConcreteBuilder2.buildPart2");
    }

    @Override
    void buildPart2() {
        product2.setPart2("part2");
        System.out.println("ConcreteBuilder2.buildPart2");
    }

    @Override
    IProduct retrieveResult() {
        return product2;
    }
}
