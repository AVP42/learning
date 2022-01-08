package info.wufc.learning.design_pattern.builder.definition;

/**
 * @ClassName: AbstractBuilder
 * @Description: 抽象builder
 * @Info: createdBy alien on 2019/12/15/015 21:40
 */
public abstract class AbstractBuilder {
    abstract void buildPart1();
    abstract void buildPart2();
    abstract IProduct retrieveResult();
}
