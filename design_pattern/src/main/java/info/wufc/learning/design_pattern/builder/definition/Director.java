package info.wufc.learning.design_pattern.builder.definition;

/**
 * @ClassName: Director
 * @Description: 导演角色
 * @Info: createdBy alien on 2019/12/15/015 21:40
 */
public class Director {
    private AbstractBuilder builder;

    public Director(AbstractBuilder builder) {
        this.builder = builder;
    }

    public IProduct construct() {
        builder.buildPart1();
        builder.buildPart2();
        return builder.retrieveResult();
    }
}
