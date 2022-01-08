package info.wufc.learning.design_pattern.strategy.solution.solution4_策略模式_装饰模式;

/**
 * @ClassName: ConcreteStrategy
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/27/027 23:31
 */
public class ConcreteStrategy implements Strategy {
    @Override
    public void doStrategy() {
        System.out.println("ConcreteStrategy.doStrategy");
    }
}
