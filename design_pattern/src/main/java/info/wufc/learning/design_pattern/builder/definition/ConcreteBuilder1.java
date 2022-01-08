package info.wufc.learning.design_pattern.builder.definition;

/**
 * @ClassName: ConcreteBuilder
 * @Description: 具体建造者
 * @Info: createdBy alien on 2019/12/15/015 21:40
 */
public class ConcreteBuilder1 extends AbstractBuilder{
    IProduct product1 = new Product1();

    @Override
    void buildPart1() {
        product1.setPart1("part1");
        System.out.println("ConcreteBuilder1.buildPart1");
    }

    /** 对于没有的性质，进行空实现*/
    @Override
    void buildPart2() {
        System.out.println("ConcreteBuilder1.doNothing");
    }

    @Override
    IProduct retrieveResult() {
        return product1;
    }
}
